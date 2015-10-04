var app = angular.module('hello', [ 'ngRoute', 'pascalprecht.translate', 'auth', 'home', 'message', 'navigation', 'cars', 'product' ]);
app.config(function($routeProvider, $httpProvider, $locationProvider) {

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
});

app.config(function($translateProvider) {

	$translateProvider.useStaticFilesLoader({
		prefix : 'js/locale-',
		suffix : '.json'
	});
	$translateProvider.lang = 'en';
	$translateProvider.preferredLanguage('en');

});
app.run(function(auth) {
	auth.init('/', '/login', '/logout');
});
