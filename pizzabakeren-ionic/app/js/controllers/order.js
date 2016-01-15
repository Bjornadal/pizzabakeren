'use strict';

angular.module('pizzabakeren').controller('OrderCtrl', function ($scope, $localstorage, $state, $ionicPopup) {
  $scope.startOrder = function () {
    var settings = $localstorage.getObject('settings');
    if (!settings.loggedIn || !settings.group) {
      $ionicPopup.alert({title: 'Du må logge på og legge inn gruppe før du kan bestille'});
    }
    else {
      $state.go('app.pizza-select');
    }
  };
});
