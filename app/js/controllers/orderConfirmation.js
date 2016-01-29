'use strict';

angular.module('pizzabakeren').controller('OrderConfirmationCtrl', function ($scope, $firebaseArray, ENV, $state, OrderFactory, $localstorage, $ionicPopup, $ionicHistory) {
  $scope.$on('$ionicView.enter', function (e) {
    var settings = $localstorage.getObject('settings');
    var ref = new Firebase(ENV.apiEndpoint + "/orders/" + settings.group + "/" + moment().format("YYYY-MM-DD"));
    var orders = $firebaseArray(ref);

    $scope.hasAccess = true;
    $scope.order = OrderFactory;
    $scope.order.user = settings.username;
    $scope.order.group = settings.group;

    orders.$loaded(function () {
      var access = true;
      angular.forEach(orders, function (order) {
        if (settings.username == order.user) {
          access = false;
        }
      });
      $scope.hasAccess = access;
    });

    $scope.saveOrder = function () {
      $scope.order.datetime = (new Date).toJSON();
      orders.$add($scope.order);
      $state.go('app.history');
      $ionicPopup.alert({title: 'Bestillingen ble sendt!'});

      $ionicHistory.nextViewOptions({
        disableAnimate: true,
        historyRoot: true
      });
    }
  });
});
