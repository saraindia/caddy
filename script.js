// script.js

    // create the module and name it scotchApp
        // also include ngRoute for all our routing needs
    var app = angular.module('myApp', ['ngRoute']);

    // configure our routes
    app.config(function($routeProvider) {
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
            })
        
            .when('/review', {
                templateUrl : 'pages/review.html',
                controller  : 'reviewController'
            })
        
            .when('/confirm', {
                templateUrl : 'pages/confirm.html',
                controller  : 'confirmController'
            });
    });

    // create the controller and inject Angular's $scope
    app.controller('mainController', function($scope) {
        // create a message to display in our view
        $scope.message = 'Welcome to my home page';
        $scope.search = function () {
          /* the $http service allows you to make arbitrary ajax requests.
           * in this case you might also consider using angular-resource and setting up a
           * User $resource. */
          $http.get('/your/url/search', { params: user },
            function (response) { $scope.results = response; },
            function (failure) { console.log("failed :(", failure); });
      }
    });

    app.controller('productController', function($scope) {
        $scope.message = 'Products page';
    });

    app.controller('evidenceController', function($scope) {
        $scope.message = 'Evidence page';
    });
    app.controller('reviewController', function($scope) {
        $scope.message = 'Review of my tickets page';
    });
    app.controller('confirmController', function($scope) {
        $scope.message = 'Confirmation page';
    });