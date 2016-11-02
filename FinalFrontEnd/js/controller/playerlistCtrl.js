mod.controller("playerlistCtrl", ["$http","teamService","offensivePlayerService","positionService", "helper","token", function($http,teamService,offensivePlayerService,positionService,helper,token) {
	var self=this;
	self.teams = null;

	positionService.refresh().then(function(resp) {
		self.positions = resp;
	});

	//Pull Team information from local db
	teamService.refresh().then(function(resp) {
		self.teams = teamService.getTeams();
		console.log("teams",self.teams);
		
		offensivePlayerService.refresh().then(function(resp) {
			self.players = offensivePlayerService.getPlayers();
		})
	});

	$http({
		url: "http://localhost:8080/offensivePlayer/byTeam",
		method: "GET",

	}).then(function(resp) {
		console.log(resp);
		self.byTeam = resp.data;
		// self.position.description = resp.data;
		self.roster = self.byTeam[self.team];
	});

	self.updateRoster = function(code) {
		console.log("code",code);
		self.roster = self.byTeam[code];
	}

}]);