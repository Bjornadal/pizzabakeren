angular.module('starter.controllers', [])

  .controller('DashCtrl', function ($scope, $localstorage, $cordovaToast, $state) {
    $scope.startOrder = function() {
      var settings = $localstorage.getObject('settings');
      if (!settings.username || !settings.group) {
        $cordovaToast
          .show('Du må legge inn navn og gruppe før du kan bestille', 'long', 'center')
          .then(function(success) {}, function (error) {});
      }
      else {
        $state.go('tab.pizza-select');
      }
    };
  })

  .controller('PizzaSelectCtrl', function ($scope, $firebaseObject, ENV, $location, $state, OrderFactory) {
    var ref = new Firebase(ENV.apiEndpoint + "/pizzaList");
    $scope.pizzaList = $firebaseObject(ref);

    $scope.selectPizza = function(pizza) {
      OrderFactory.pizzaName = pizza.name;
      OrderFactory.pizzaNr = pizza.nr;
      OrderFactory.price = pizza.price;
      $state.go('tab.soda-select');
    }
  })

  .controller('SodaSelectCtrl', function ($scope, $firebaseObject, ENV, $state, OrderFactory) {
    var ref = new Firebase(ENV.apiEndpoint + "/sodaList");
    $scope.sodaList = $firebaseObject(ref);

    $scope.selectSoda = function(soda) {
      OrderFactory.soda = soda.name;
      $state.go('tab.confirmation');
    }
  })

  .controller('OrderConfirmationCtrl', function ($scope, $firebaseArray, ENV, $state, OrderFactory, $cordovaToast, $localstorage) {
    var settings = $localstorage.getObject('settings');
    var ref = new Firebase(ENV.apiEndpoint + "/orders/" + settings.group + "/" + moment().format("YYYY-MM-DD"));
    var orders = $firebaseArray(ref);

    $scope.order = OrderFactory;

    $scope.saveOrder = function() {
      $scope.order.datetime = (new Date).toJSON();
      $scope.order.user = settings.username;
      $scope.order.group = settings.group;
      orders.$add($scope.order);
      $state.go('tab.dash');

      $cordovaToast
        .show('Bestillingen ble sendt!', 'long', 'center')
        .then(function(success) {}, function (error) {});
    }
  })

  .controller('HistoryCtrl', function ($scope, $firebaseArray, ENV, $localstorage) {
    // With the new view caching in Ionic, Controllers are only called
    // when they are recreated or on app start, instead of every page change.
    // To listen for when this page is active (for example, to refresh data),
    // listen for the $ionicView.enter event:
    //
    $scope.$on('$ionicView.enter', function(e) {
      $scope.settings = $localstorage.getObject('settings');
      var ref = new Firebase(ENV.apiEndpoint + "/orders/" + $scope.settings.group + "/" + moment().format("YYYY-MM-DD"));
      $scope.orders = $firebaseArray(ref);

      $scope.pizzaSummary = {};
      $scope.sodaSummary = {};

      $scope.orders.$loaded(function() {
        angular.forEach($scope.orders, function(value, key) {
          $scope.pizzaSummary[value.pizzaNr] = ($scope.pizzaSummary[value.pizzaNr] == null) ? 1 : $scope.pizzaSummary[value.pizzaNr]+1;
          $scope.sodaSummary[value.soda] = ($scope.sodaSummary[value.soda] == null) ? 1 : $scope.sodaSummary[value.soda]+1;
        });
      });
    });
  })

  .controller('OrderDetailCtrl', function ($scope, $stateParams) {
    $scope.chat = [{}];
  })

  .controller('SettingsCtrl', function ($scope, $localstorage, $cordovaToast) {
    $scope.settings = $localstorage.getObject('settings');

    $scope.save = function() {
      $scope.settings.group = $scope.settings.group.toLowerCase();
      $localstorage.setObject('settings', $scope.settings);

      $cordovaToast
        .show('Innstillinger ble lagret', 'long', 'center')
        .then(function(success) {}, function (error) {});
    }
  });
