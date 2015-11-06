angular.module('starter.services', [])

  .factory('OrderFactory', function () {
    var order = {
      pizzaNr: 1,
      pizzaName: "DEN ENKLE",
      soda: "Cola",
      group: "test",
      user: "test-user",
      price: 0
    };
    return order;
  });
