var module = angular.module('starter.services', []);

module.factory('OrderFactory', function () {
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
});

module.factory('$localstorage', ['$window', function ($window) {
  return {
    set: function (key, value) {
      $window.localStorage[key] = value;
    },
    get: function (key, defaultValue) {
      return $window.localStorage[key] || defaultValue;
    },
    setObject: function (key, value) {
      $window.localStorage[key] = JSON.stringify(value);
    },
    getObject: function (key) {
      return JSON.parse($window.localStorage[key] || '{}');
    }
  }
}]);

module.service('CalculateService', function () {
  this.calculateSummary = function (orders) {
    var summary = {
      pizza: [],
      soda: []
    };

    var pizzaSummaryTmp = {};
    var sodaSummaryTmp = {};

    angular.forEach(orders, function (value, key) {
      pizzaSummaryTmp[value.pizzaNr] = (pizzaSummaryTmp[value.pizzaNr] == null) ? 1 : pizzaSummaryTmp[value.pizzaNr] + 1;
      sodaSummaryTmp[value.soda] = (sodaSummaryTmp[value.soda] == null) ? 1 : sodaSummaryTmp[value.soda] + 1;
    });
    angular.forEach(pizzaSummaryTmp, function (value, key) {
      summary.pizza.push({
        "nr": parseInt(key),
        "count": value
      });
    });
    angular.forEach(sodaSummaryTmp, function (value, key) {
      summary.soda.push({
        "name": key,
        "count": value
      });
    });

    return summary;
  }
});
