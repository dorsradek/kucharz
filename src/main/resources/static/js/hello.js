angular
		.module('hello',
				[ 'ngRoute', 'auth', 'home', 'message', 'navigation', 'cars', 'product' ])
		.config(

				function($routeProvider, $httpProvider, $locationProvider) {

					$locationProvider.html5Mode(true);

					$routeProvider.when('/', {
						templateUrl : 'js/home/home.html',
						controller : 'home'
					}).when('/message', {
						templateUrl : 'js/message/message.html',
						controller : 'message'
					}).when('/login', {
						templateUrl : 'js/navigation/login.html',
						controller : 'navigation'
					}).when('/cars', {
						templateUrl : 'js/cars/cars.html',
						controller : 'cars',
					}).when('/product', {
						templateUrl : 'js/product/product.html',
						controller : 'product',
					}).otherwise('/');

					$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

				}).run(function(auth) {

			// Initialize auth module with the home page and login/logout path
			// respectively
			auth.init('/', '/login', '/logout');

		});
