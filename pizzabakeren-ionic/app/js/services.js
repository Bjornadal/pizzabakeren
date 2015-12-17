var module = angular.module('starter.services', []);

module.factory('OrderFactory', function () {
  var order = {
    pizzaNr: 1,
    pizzaName: "DEN ENKLE",
    soda: "Cola",
    group: "test",
    user: "test-user",
    price: 0,
    dateTime: null
  };
  return order;
});

module.factory('$localstorage', ['$window', function ($window) {
  return {
    set: function (key, value) {
      $window.localStorage[key] = value;
    },
    get: function (key, defaultValue) {
      return $window.localStorage[key] || defaultValue;
    },
    setObject: function (key, value) {
      $window.localStorage[key] = JSON.stringify(value);
    },
    getObject: function (key) {
      return JSON.parse($window.localStorage[key] || '{}');
    }
  }
}]);

module.service('CalculateService', function () {
  this.calculateSummary = function (orders) {
    var summary = {
      pizza: [],
      soda: []
    };

    var pizzaSummaryTmp = {};
    var sodaSummaryTmp = {};

    angular.forEach(orders, function (value, key) {
      pizzaSummaryTmp[value.pizzaNr] = (pizzaSummaryTmp[value.pizzaNr] == null) ? 1 : pizzaSummaryTmp[value.pizzaNr] + 1;
      sodaSummaryTmp[value.soda] = (sodaSummaryTmp[value.soda] == null) ? 1 : sodaSummaryTmp[value.soda] + 1;
    });
    angular.forEach(pizzaSummaryTmp, function (value, key) {
      summary.pizza.push({
        "nr": parseInt(key),
        "count": value
      });
    });
    angular.forEach(sodaSummaryTmp, function (value, key) {
      summary.soda.push({
        "name": key,
        "count": value
      });
    });

    return summary;
  }
});

module.service('$updateService', function($cordovaFileTransfer, $cordovaFileOpener2, $firebaseObject, ENV, $utilsService, $ionicPopup, $q) {
  this.isUpdateAvailable = function() {
    var deferred = $q.defer()
    var ref = new Firebase(ENV.apiEndpoint + "/app");
    var app = $firebaseObject(ref);

    app.$loaded(function () {
      cordova.getAppVersion.getVersionNumber(function (version) {
        // If update is available
        if ($utilsService.compareVersions(app.version, version) >= 1) {
          var confirmPopup = $ionicPopup.confirm({title: 'En oppdatering er tilgjengelig. Trykk "OK" for oppdatere.'});
          confirmPopup.then(function(res) {
            if(res) {
              deferred.resolve(true);
            } else {
              deferred.resolve(false);
            }
          });
        }
      });
    });

    return deferred.promise;
  };

  this.installUpdate = function() {
    var url = ENV.updateEndpoint + "pizzabakeren.apk";
    var targetPath = cordova.file.externalRootDirectory + "/Download/pizzabakeren.apk";
    var trustHosts = true
    var options = {};

    $cordovaFileTransfer.download(url, targetPath, options, trustHosts)
      .then(function(result) {
        $cordovaFileOpener2.open(targetPath, 'application/vnd.android.package-archive')
          .then(function() {
            // Success!
          }, function(err) {
            // An error occurred. Show a message to the user
          });
      }, function(err) {
        // Error
      }, function (progress) {
        $timeout(function () {
          console.log("Progress: " + (progress.loaded / progress.total) * 100);
        })
      });
  };
});

module.service('$utilsService', function() {
  this.compareVersions = function(v1, v2, options) {
    var lexicographical = options && options.lexicographical,
      zeroExtend = options && options.zeroExtend,
      v1parts = v1.split('.'),
      v2parts = v2.split('.');

    function isValidPart(x) {
      return (lexicographical ? /^\d+[A-Za-z]*$/ : /^\d+$/).test(x);
    }

    if (!v1parts.every(isValidPart) || !v2parts.every(isValidPart)) {
      return NaN;
    }

    if (zeroExtend) {
      while (v1parts.length < v2parts.length) v1parts.push("0");
      while (v2parts.length < v1parts.length) v2parts.push("0");
    }

    if (!lexicographical) {
      v1parts = v1parts.map(Number);
      v2parts = v2parts.map(Number);
    }

    for (var i = 0; i < v1parts.length; ++i) {
      if (v2parts.length == i) {
        return 1;
      }

      if (v1parts[i] == v2parts[i]) {
        continue;
      }
      else if (v1parts[i] > v2parts[i]) {
        return 1;
      }
      else {
        return -1;
      }
    }

    if (v1parts.length != v2parts.length) {
      return -1;
    }

    return 0;
  }
});
