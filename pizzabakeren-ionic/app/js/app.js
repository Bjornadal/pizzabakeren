// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'starter.controllers', 'starter.services', 'firebase', 'config', 'ngCordova', 'starter.filters', 'angular.filter'])

  .run(function ($ionicPlatform) {
    $ionicPlatform.ready(function () {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
        cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
        cordova.plugins.Keyboard.disableScroll(true);

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

      .state('app.dash', {
        url: '/dash',
        views: {
          'menuContent': {
            templateUrl: 'templates/tabs/tab-dash.html',
            controller: 'DashCtrl'
          }
        }
      })
      .state('app.pizza-select', {
        url: '/dash/pizza',
        views: {
          'menuContent': {
            templateUrl: 'templates/order/pizza-select.html',
            controller: 'PizzaSelectCtrl'
          }
        }
      })
      .state('app.soda-select', {
        url: '/dash/pizza/soda',
        views: {
          'menuContent': {
            templateUrl: 'templates/order/soda-select.html',
            controller: 'SodaSelectCtrl'
          }
        }
      })
      .state('app.confirmation', {
        url: '/dash/pizza/soda/confirm',
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
            templateUrl: 'templates/tabs/tab-history.html',
            controller: 'HistoryCtrl'
          }
        }
      })

      .state('app.settings', {
        url: '/settings',
        views: {
          'menuContent': {
            templateUrl: 'templates/tabs/tab-settings.html',
            controller: 'SettingsCtrl'
          }
        }
      });

    // if none of the above states are matched, use this as the fallback
    $urlRouterProvider.otherwise('/app/dash');

  });
