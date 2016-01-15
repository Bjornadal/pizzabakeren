'use strict';

angular.module('pizzabakeren').controller('SettingsCtrl', function ($scope, $localstorage) {

  $scope.settings = $localstorage.getObject('settings');

  $scope.changed = function () {
    $scope.settings.group = $scope.settings.group.toLowerCase();
    $localstorage.setObject('settings', $scope.settings);
  };
});
