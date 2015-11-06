angular.module('starter.controllers', [])

  .controller('DashCtrl', function ($scope) {
  })

  .controller('PizzaSelectCtrl', function ($scope, $firebaseObject, ENV, $location, $state) {
    var ref = new Firebase(ENV.apiEndpoint + "/pizzaList");
    $scope.pizzaList = $firebaseObject(ref);

    $scope.selectPizza = function(pizza) {
      $state.go('tab.soda-select');
    }
  })

  .controller('SodaSelectCtrl', function ($scope, $firebaseObject, ENV) {
    var ref = new Firebase(ENV.apiEndpoint + "/sodaList");
    $scope.sodaList = $firebaseObject(ref);

    $scope.selectSoda = function(soda) {
      //$state.go('tab.soda-select');
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
