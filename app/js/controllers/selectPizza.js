'use strict';

angular.module('pizzabakeren').controller('PizzaSelectCtrl', function ($scope, $firebaseArray, ENV, $location, $state, OrderFactory) {
  var ref = new Firebase(ENV.apiEndpoint + "/pizzaList");
  $scope.pizzaList = $firebaseArray(ref);
  $scope.selectPizza = function (pizza) {
    OrderFactory.pizzaName = pizza.name;
    OrderFactory.pizzaNr = pizza.nr;
    OrderFactory.price = pizza.price;
    $state.go('app.soda-select');
  }
});
