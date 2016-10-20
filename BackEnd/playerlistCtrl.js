mod.controller("playerlistCtrl", ["$http","helper","token", function($http,helper,token) {
	var self=this;
	self.teams = null;

	
	$http({
		url: "http://localhost:8080/team",
		method: "GET",
		

	}).then(function(resp) {
		console.log(resp);
		self.teamName = resp.data;
	});
}]);