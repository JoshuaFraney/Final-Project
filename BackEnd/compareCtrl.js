mod.controller("compareCtrl", ["$http","helper","token", function($http,helper,token) {
	var self=this;
	self.teams = null;
	var teamAbrev = null;

	
	$http({
		url: "http://localhost:8080/team",
		method: "GET",

	}).then(function(resp) {
		console.log(resp);
		self.teamName = resp.data;
	});

	$http({
		url: "http://localhost:8080/offensivePlayer/team/" + teamAbrev,
		method: "GET",

	}).then(function(resp) {
		console.log(resp);
		self.playrName = resp.data;
		// self.position.description = resp.data;

		});

}]);