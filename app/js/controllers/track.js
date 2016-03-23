'use strict';

angular.module('pizzabakeren').controller('TrackCtrl', function ($scope, $cordovaGeolocation, $firebaseObject, ENV, $localstorage, $interval, $cordovaBackgroundGeolocation) {
    var watch;
    var marker;
    var bgLocationServices = (window.plugins) ? window.plugins.backgroundLocationServices : null;
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

    syncTrack.$loaded(function () {
        var pos = new google.maps.LatLng($scope.track.position.latitude, $scope.track.position.longitude);
        $scope.map.setCenter(pos);
        marker = new google.maps.Marker({
            position: pos,
            map: $scope.map,
            title: 'Pizza'
        });

        $scope.$watch('track.position.latitude', function () {
            var pos = new google.maps.LatLng($scope.track.position.latitude, $scope.track.position.longitude);
            $scope.map.setCenter(pos);
            marker.setPosition(pos);
        });
    });

    $scope.stopTracking = function () {
        $scope.track.live = false;
        $scope.track.user = '';
        bgLocationServices.stop();

        if (angular.isDefined(watch)) {
            watch.clearWatch();
            watch = undefined;
        }
    };

    $scope.startTracking = function () {
        $scope.track.live = true;
        $scope.track.user = settings.username;

        $scope.startForegroundGps();
        $scope.startBackgroundGps();
    };

    $scope.startBackgroundGps = function() {
        var posOptions = {maximumAge: 5000, timeout: 60000, enableHighAccuracy: true};
        navigator.geolocation.getCurrentPosition(
            function(position) {
            },
            function(err) {
                console.log(err);
            },
        posOptions);

        //Congfigure Plugin
        bgLocationServices.configure({
            //Both
            desiredAccuracy: 20, // Desired Accuracy of the location updates (lower means more accurate but more battery consumption)
            distanceFilter: 5, // (Meters) How far you must move from the last point to trigger a location update
            debug: false, // <-- Enable to show visual indications when you receive a background location update
            interval: 9000, // (Milliseconds) Requested Interval in between location updates.
            //Android Only
            notificationTitle: 'Pizzabakeren', // customize the title of the notification
            notificationText: 'GPS sporing aktivert', //customize the text of the notification
            fastestInterval: 5000, // <-- (Milliseconds) Fastest interval your app / server can handle updates
            useActivityDetection: true // Uses Activitiy detection to shut off gps when you are still (Greatly enhances Battery Life)
        });

        bgLocationServices.registerForLocationUpdates(function(location) {
            console.log("We got an BG Update" + JSON.stringify(location));
            syncTrack.position = {
                latitude: location.latitude,
                longitude: location.longitude,
                speed: location.speed,
                altitude: location.altitude,
                timestamp: location.timestamp
            };;
            syncTrack.$save();
        }, function(err) {
            console.log("Error: Didnt get an update", err);
        });

        //Start the Background Tracker. When you enter the background tracking will start, and stop when you enter the foreground.
        bgLocationServices.start();
    };

    $scope.startForegroundGps = function () {
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
                    $scope.track.position = {
                        latitude: position.coords.latitude,
                        longitude: position.coords.longitude,
                        speed: position.coords.speed,
                        altitude: position.coords.altitude,
                        timestamp: position.timestamp
                    };
                });
        }
    }
});
