// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
angular.module('pizzabakeren', ['ionic', 'firebase', 'config', 'ngCordova', 'angular.filter'])

  .run(function ($ionicPlatform, $updateService) {
    $ionicPlatform.ready(function () {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      if (window.cordova) {
        if (window.cordova.plugins && window.cordova.plugins.Keyboard) {
          cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
          cordova.plugins.Keyboard.disableScroll(true);
        }

        $updateService.isUpdateAvailable().then(function (install) {
          if (install) {
            $updateService.installUpdate();
          }
        });
      }

      if (window.StatusBar) {
        // org.apache.cordova.statusbar required
        StatusBar.styleDefault();
      }
    });
  })

  .config(function ($stateProvider, $urlRouterProvider) {

    // Ionic uses AngularUI Router which uses the concept of states
    // Learn more here: https://github.com/angular-ui/ui-router
    // Set up the various states which the app can be in.
    // Each state's controller can be found in controllers.js
    $stateProvider

    // setup an abstract state for the tabs directive
      .state('app', {
        url: '/app',
        abstract: true,
        templateUrl: 'templates/menu/menu.html',
        controller: 'AppCtrl'
      })

      // Each tab has its own nav history stack:

      .state('app.order', {
        url: '/order',
        views: {
          'menuContent': {
            templateUrl: 'templates/order/startOrder.html',
            controller: 'OrderCtrl'
          }
        }
      })
      .state('app.pizza-select', {
        url: '/order/pizza',
        views: {
          'menuContent': {
            templateUrl: 'templates/order/select-pizza.html',
            controller: 'PizzaSelectCtrl'
          }
        }
      })
      .state('app.soda-select', {
        url: '/order/pizza/soda',
        views: {
          'menuContent': {
            templateUrl: 'templates/order/select-soda.html',
            controller: 'SodaSelectCtrl'
          }
        }
      })
      .state('app.confirmation', {
        url: '/order/pizza/soda/confirm',
        views: {
          'menuContent': {
            templateUrl: 'templates/order/confirmation.html',
            controller: 'OrderConfirmationCtrl'
          }
        }
      })

      .state('app.history', {
        url: '/history',
        views: {
          'menuContent': {
            templateUrl: 'templates/history/history.html',
            controller: 'HistoryCtrl'
          }
        }
      })

      .state('app.settings', {
        url: '/settings',
        views: {
          'menuContent': {
            templateUrl: 'templates/settings/settings.html',
            controller: 'SettingsCtrl'
          }
        }
      });

    // if none of the above states are matched, use this as the fallback
    $urlRouterProvider.otherwise('/app/order');

  });
