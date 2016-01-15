'use strict';

angular.module('pizzabakeren').controller('HistoryCtrl', function ($scope, $firebaseArray, ENV, $localstorage, CalculateService) {
  $scope.$on('$ionicView.enter', function (e) {
    $scope.settings = $localstorage.getObject('settings');
    var ref = new Firebase(ENV.apiEndpoint + "/orders/" + $scope.settings.group + "/" + moment().format("YYYY-MM-DD"));
    $scope.orders = $firebaseArray(ref);

    $scope.orders.$watch(function (event) {
      $scope.summary = CalculateService.calculateSummary($scope.orders);
    });
  });
});
