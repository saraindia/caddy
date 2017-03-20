// script.js

    // create the module and name it scotchApp
        // also include ngRoute for all our routing needs
    var scotchApp = angular.module('myApp', ['ngRoute']);

    // configure our routes
    scotchApp.config(function($routeProvider) {
        $routeProvider

            // route for the home page
            .when('/', {
                templateUrl : 'pages/categories.html',
                controller  : 'mainController'
            })

            // route for the about page
            .when('/product', {
                templateUrl : 'pages/product.html',
                controller  : 'productController'
            })

            // route for the contact page
            .when('/evidence', {
                templateUrl : 'pages/evidence.html',
                controller  : 'evidenceController'
            });
        
            .when('/review', {
                templateUrl : 'pages/review.html',
                controller  : 'reviewController'
            });
        
            .when('/confirm', {
                templateUrl : 'pages/confirm.html',
                controller  : 'confirmController'
            });
    });

    // create the controller and inject Angular's $scope
    scotchApp.controller('mainController', function($scope) {
        // create a message to display in our view
        $scope.message = 'Welcome to my home page';
    });

    scotchApp.controller('aboutController', function($scope) {
        $scope.message = 'About page';
    });

    scotchApp.controller('contactController', function($scope) {
        $scope.message = 'contact us page';
    });