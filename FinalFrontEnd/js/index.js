var mod = angular.module("AppMod", ['ngRoute']);

mod.controller("appCtrl", ["$http","$location", function($http,$location) { 
	var self = this;
	
	self.route = function(url) {
		$location.path(url);
	}
}]);
		
