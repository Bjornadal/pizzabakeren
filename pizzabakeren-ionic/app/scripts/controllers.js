angular.module('starter.controllers', [])

  .controller('DashCtrl', function ($scope) {
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

  .controller('OrderConfirmationCtrl', function ($scope, $firebaseArray, ENV, $state, OrderFactory, $cordovaToast) {
    var ref = new Firebase(ENV.apiEndpoint + "/orders");
    var orders = $firebaseArray(ref);

    $scope.order = OrderFactory;

    $scope.saveOrder = function() {
      $scope.order.date = (new Date()).toJSON();
      orders.$add($scope.order);
      $state.go('tab.dash');

      $cordovaToast
        .show('Bestillingen ble sendt!', 'long', 'center')
        .then(function(success) {
          // success
        }, function (error) {
          // error
        });

    }
  })

  .controller('OrdersCtrl', function ($scope, Chats) {
    // With the new view caching in Ionic, Controllers are only called
    // when they are recreated or on app start, instead of every page change.
    // To listen for when this page is active (for example, to refresh data),
    // listen for the $ionicView.enter event:
    //
    //$scope.$on('$ionicView.enter', function(e) {
    //});

    $scope.chats = Chats.all();
    $scope.remove = function (chat) {
      Chats.remove(chat);
    };
  })

  .controller('OrderDetailCtrl', function ($scope, $stateParams, Chats) {
    $scope.chat = Chats.get($stateParams.chatId);
  })

  .controller('SettingsCtrl', function ($scope) {
    $scope.settings = {
      enableFriends: true
    };

  });
