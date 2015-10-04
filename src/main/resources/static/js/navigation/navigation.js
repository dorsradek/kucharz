angular.module('navigation', [ 'ngRoute', 'auth' ]).controller('navigation',

function($scope, $route, auth, $translate) {

	$scope.credentials = {};

	$scope.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};

	$scope.authenticated = function() {
		return auth.authenticated;
	};

	$scope.isUser = function() {
		return auth.authenticated && (auth.role == 'ROLE_ADMIN' || auth.role == 'ROLE_USER');
	};

	$scope.isAdmin = function() {
		return auth.authenticated && auth.role == 'ROLE_ADMIN';
	};

	$scope.login = function() {
		auth.authenticate($scope.credentials, function(authenticated) {
			if (authenticated) {
				console.log("Login succeeded");
				$scope.error = false;
			} else {
				console.log("Login failed");
				$scope.error = true;
			}
		});
	};

	$scope.changeLanguage = function(langKey) {
		$translate.lang = langKey;
		$translate.use(langKey);
	};
	
	$scope.getLanguage = function() {
		if ($translate.lang) {
			return $translate.lang;
		}
		return 'en';
	};

	$scope.logout = auth.clear;

});
