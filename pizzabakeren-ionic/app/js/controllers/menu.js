'use strict';

angular.module('pizzabakeren').controller('AppCtrl', function ($scope, $localstorage, ENV) {
  $scope.$on('$ionicView.enter', function (e) {

    $scope.settings = $localstorage.getObject('settings');

    var ref = new Firebase(ENV.apiEndpoint);
    ref.onAuth(function (authData) {
      if (authData != null) {
        $scope.settings.loggedIn = true;
        $scope.settings.username = authData.twitter.displayName;
        $scope.settings.profileImageURL = authData.twitter.profileImageURL.replace("_normal", "");
        $localstorage.setObject('settings', $scope.settings);
      }
    });

    $scope.logout = function () {
      ref.unauth();
      $scope.settings.loggedIn = false;
      $scope.settings.username = '';
      $scope.settings.profileImageURL = 'img/profile-img.jpg';
      $localstorage.setObject('settings', $scope.settings);
    };

    $scope.login = function () {
      ref.authWithOAuthPopup("twitter", function (error, authData) {
        if (error) {
          console.log("Login Failed!", error);
        } else {
          console.log("Authenticated successfully with payload:", authData);
          $scope.$apply();
        }
      });
    };
  });
});
