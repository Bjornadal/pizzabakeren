var module = angular.module('starter.controllers', []);


module.controller('AppCtrl', function ($scope, $localstorage, $state) {
  $scope.settings = $localstorage.getObject('settings');
});


module.controller('DashCtrl', function ($scope, $localstorage, $state, $ionicPopup) {
  $scope.startOrder = function () {
    var settings = $localstorage.getObject('settings');
    if (!settings.username || !settings.group) {
      $ionicPopup.alert({title: 'Du må logge på og legge inn gruppe før du kan bestille'});
    }
    else {
      $state.go('app.pizza-select');
    }
  };
});

module.controller('PizzaSelectCtrl', function ($scope, $firebaseArray, ENV, $location, $state, OrderFactory) {
  var ref = new Firebase(ENV.apiEndpoint + "/pizzaList");
  $scope.pizzaList = $firebaseArray(ref);
  $scope.selectPizza = function (pizza) {
    OrderFactory.pizzaName = pizza.name;
    OrderFactory.pizzaNr = pizza.nr;
    OrderFactory.price = pizza.price;
    $state.go('app.soda-select');
  }
});

module.controller('SodaSelectCtrl', function ($scope, $firebaseArray, ENV, $state, OrderFactory) {
  var ref = new Firebase(ENV.apiEndpoint + "/sodaList");
  $scope.sodaList = $firebaseArray(ref);
  $scope.selectSoda = function (soda) {
    OrderFactory.soda = soda.name;
    $state.go('app.confirmation');
  }
});

module.controller('OrderConfirmationCtrl', function ($scope, $firebaseArray, ENV, $state, OrderFactory, $localstorage, $ionicPopup) {
  $scope.$on('$ionicView.enter', function (e) {
    var settings = $localstorage.getObject('settings');
    var ref = new Firebase(ENV.apiEndpoint + "/orders/" + settings.group + "/" + moment().format("YYYY-MM-DD"));
    var orders = $firebaseArray(ref);

    $scope.buttonDisabled = true;
    $scope.order = OrderFactory;
    $scope.order.user = settings.username;
    $scope.order.group = settings.group;

    orders.$loaded(function () {
      var disabled = false;
      angular.forEach(orders, function (order) {
        if (settings.username == order.user) {
          disabled = true;
        }
      });
      $scope.buttonDisabled = disabled;
    });

    $scope.saveOrder = function () {
      $scope.order.datetime = (new Date).toJSON();
      orders.$add($scope.order);
      $state.go('app.dash');
      $ionicPopup.alert({title: 'Bestillingen ble sendt!'});
    }
  });
});

module.controller('HistoryCtrl', function ($scope, $firebaseArray, ENV, $localstorage, CalculateService) {
  $scope.$on('$ionicView.enter', function (e) {
    $scope.settings = $localstorage.getObject('settings');
    var ref = new Firebase(ENV.apiEndpoint + "/orders/" + $scope.settings.group + "/" + moment().format("YYYY-MM-DD"));
    $scope.orders = $firebaseArray(ref);

    $scope.orders.$watch(function (event) {
      $scope.summary = CalculateService.calculateSummary($scope.orders);
    });
  });
});

module.controller('SettingsCtrl', function ($scope, $localstorage, ENV) {
  var ref = new Firebase(ENV.apiEndpoint);

  $scope.settings = $localstorage.getObject('settings');

  ref.onAuth(function(authData) {
    if (authData != null) {
      $scope.settings.loggedIn = true;
      $scope.settings.username = authData.twitter.displayName;
      $scope.settings.profileImageURL = authData.twitter.profileImageURL.replace("_normal", "");
      $localstorage.setObject('settings', $scope.settings);
    }
  });

  $scope.logout = function() {
    ref.unauth();
    $scope.settings.loggedIn = false;
    $scope.settings.username = null;
    $scope.settings.profileImageURL = null;
    $localstorage.setObject('settings', $scope.settings);
  };

  $scope.login = function() {
    ref.authWithOAuthPopup("twitter", function(error, authData) {
      if (error) {
        console.log("Login Failed!", error);
      } else {
        console.log("Authenticated successfully with payload:", authData);
        $scope.$apply();
      }
    });
  };

  $scope.changed = function() {
    $scope.settings.group = $scope.settings.group.toLowerCase();
    $localstorage.setObject('settings', $scope.settings);
  };
});
