'use strict';

angular.module('pizzabakeren').service('CalculateService', function () {
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
