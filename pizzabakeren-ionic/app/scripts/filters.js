/**
 * Created by andreasb on 06.11.15.
 */
angular.module('starter.filters', [])
  .filter('capitalize', function() {
    return function(input) {
      return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
    }
  });
