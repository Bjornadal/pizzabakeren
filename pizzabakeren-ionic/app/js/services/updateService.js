'use strict';

angular.module('pizzabakeren').service('$updateService', function ($cordovaFileTransfer, $cordovaFileOpener2, $firebaseObject, ENV, $utilsService, $ionicPopup, $q) {
  this.isUpdateAvailable = function () {
    var deferred = $q.defer()
    var ref = new Firebase(ENV.apiEndpoint + "/app");
    var app = $firebaseObject(ref);

    app.$loaded(function () {
      cordova.getAppVersion.getVersionNumber(function (version) {
        // If update is available
        if ($utilsService.compareVersions(app.version, version) >= 1) {
          var confirmPopup = $ionicPopup.confirm({title: 'En oppdatering er tilgjengelig. Trykk "OK" for oppdatere.'});
          confirmPopup.then(function (res) {
            if (res) {
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

  this.installUpdate = function () {
    var url = ENV.updateEndpoint + "pizzabakeren.apk";
    var targetPath = cordova.file.externalRootDirectory + "/Download/pizzabakeren.apk";
    var trustHosts = true
    var options = {};

    $cordovaFileTransfer.download(url, targetPath, options, trustHosts)
      .then(function (result) {
        $cordovaFileOpener2.open(targetPath, 'application/vnd.android.package-archive')
          .then(function () {
            // Success!
          }, function (err) {
            // An error occurred. Show a message to the user
          });
      }, function (err) {
        // Error
      }, function (progress) {
        $timeout(function () {
          console.log("Progress: " + (progress.loaded / progress.total) * 100);
        })
      });
  };
});
