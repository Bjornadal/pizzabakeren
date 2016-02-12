'use strict';

angular.module('pizzabakeren').controller('TrackCtrl', function ($scope, $cordovaGeolocation, $firebaseObject, ENV, $localstorage) {
    var settings = $localstorage.getObject('settings');
    var ref = new Firebase(ENV.apiEndpoint + "/track/" + settings.group);
    var syncTrack = $firebaseObject(ref);
    syncTrack.$bindTo($scope, "track");
    $scope.localUser = settings.username;

    // Load map
    function intitializeMap() {
        var myLatlng = new google.maps.LatLng(66.3016321, 14.121699);
        var mapOptions = {
            center: myLatlng,
            zoom: 16,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map"), mapOptions);
        $scope.map = map;
    }

    intitializeMap();

    var marker;
    syncTrack.$loaded(function () {
        var pos = new google.maps.LatLng($scope.track.lat, $scope.track.long);
        $scope.map.setCenter(pos);
        marker = new google.maps.Marker({
            position: pos,
            map: $scope.map,
            title: 'Pizza'
        });

        $scope.$watch('track.lat', function () {
            var pos = new google.maps.LatLng($scope.track.lat, $scope.track.long);
            $scope.map.setCenter(pos);
            marker.setPosition(pos);
        });
    });

    var watch;
    $scope.stopTracking = function () {
        $scope.track.live = false;
        $scope.track.user = '';

        if (angular.isDefined(watch)) {
            watch.clearWatch();
            watch = undefined;
            cordova.plugins.backgroundMode.disable();
        }
    };

    $scope.startTracking = function () {
        $scope.track.live = true;
        $scope.track.user = settings.username;

        // Run tracking in background
        document.addEventListener('deviceready', function () {
            cordova.plugins.backgroundMode.setDefaults({
                title: 'Pizzabakeren',
                text: 'GPS sporing aktivert.'
            });
            cordova.plugins.backgroundMode.enable();

            //cordova.plugins.backgroundMode.onactivate = function () {
            //}

            $scope.startGps();

        }, false);

    };

    $scope.startGps = function () {
        if (!angular.isDefined(watch)) {
            var watchOptions = {
                maximumAge: 5000,
                timeout: 60000,
                enableHighAccuracy: true
            };

            watch = $cordovaGeolocation.watchPosition(watchOptions);

            watch.then(
                null,
                function (err) {
                    console.error(err);
                },
                function (position) {
                    $scope.track.lat = position.coords.latitude;
                    $scope.track.long = position.coords.longitude;
                });
        }
    }
});
