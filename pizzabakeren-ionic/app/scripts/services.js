angular.module('starter.services', [])

  .factory('OrderFactory', function () {
    var order = {
      pizzaNr: 1,
      pizzaName: "DEN ENKLE",
      soda: "Cola",
      group: "test",
      user: "test-user",
      price: 0,
      dateTime: null
    };
    return order;
  })
  .factory('$localstorage', ['$window', function($window) {
    return {
      set: function(key, value) {
        $window.localStorage[key] = value;
      },
      get: function(key, defaultValue) {
        return $window.localStorage[key] || defaultValue;
      },
      setObject: function(key, value) {
        $window.localStorage[key] = JSON.stringify(value);
      },
      getObject: function(key) {
        return JSON.parse($window.localStorage[key] || '{}');
      }
    }
  }]);
