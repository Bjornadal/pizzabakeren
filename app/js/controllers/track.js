'use strict';

angular.module('pizzabakeren').controller('TrackCtrl', function ($scope, $cordovaGeolocation, $firebaseObject, ENV, $localstorage, $interval, $cordovaBackgroundGeolocation) {
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

            cordova.plugins.backgroundMode.onactivate = function () {
                console.log("on activate!");
            };

            cordova.plugins.backgroundMode.ondeactivate = function () {
                console.log("on deactivate!");
            };

            $scope.startGps();

           // startBackgroundGps();
        }, false);

    };

    function startBackgroundGps() {
        console.log("Background update interval");

        var posOptions = {maximumAge: 5000, timeout: 60000, enableHighAccuracy: true};
        navigator.geolocation.getCurrentPosition(
            function(position) {
                console.log(position);
            },
            function(err) {
                console.log(err);
            },
        posOptions);
        var bgLocationServices = window.plugins.backgroundLocationServices;

        //Congfigure Plugin
        bgLocationServices.configure({
            //Both
            desiredAccuracy: 20, // Desired Accuracy of the location updates (lower means more accurate but more battery consumption)
            distanceFilter: 5, // (Meters) How far you must move from the last point to trigger a location update
            debug: true, // <-- Enable to show visual indications when you receive a background location update
            interval: 9000, // (Milliseconds) Requested Interval in between location updates.
            //Android Only
            notificationTitle: 'BG Plugin', // customize the title of the notification
            notificationText: 'Tracking', //customize the text of the notification
            fastestInterval: 5000, // <-- (Milliseconds) Fastest interval your app / server can handle updates
            useActivityDetection: true // Uses Activitiy detection to shut off gps when you are still (Greatly enhances Battery Life)
        });

        bgLocationServices.registerForLocationUpdates(function(location) {
            console.log("We got an BG Update" + JSON.stringify(location));
        }, function(err) {
            console.log("Error: Didnt get an update", err);
        });

        //Start the Background Tracker. When you enter the background tracking will start, and stop when you enter the foreground.
        bgLocationServices.start();
    }

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
