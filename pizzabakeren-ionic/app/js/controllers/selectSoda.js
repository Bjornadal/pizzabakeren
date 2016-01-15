'use strict';

angular.module('pizzabakeren').controller('SodaSelectCtrl', function ($scope, $firebaseArray, ENV, $state, OrderFactory) {
  var ref = new Firebase(ENV.apiEndpoint + "/sodaList");
  $scope.sodaList = $firebaseArray(ref);
  $scope.selectSoda = function (soda) {
    OrderFactory.soda = soda.name;
    $state.go('app.confirmation');
  }
});
