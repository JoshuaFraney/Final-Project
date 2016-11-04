mod.controller("playerCompCtrl",["teamService","offensivePlayerService", function(teamService,offensivePlayerService) {
	var self = this;
	
	self.conf1 = "";
	self.team1 = "";
	self.player1 = "";
	self.playerStats = {"1": {vis: false, avgScore: 0.0}, "2": {vis: false, avgScore: 0.0}};
	self.start = {vis: false, "1": false, "2": false};
	
	teamService.refresh().then(function(){
		self.teams = teamService.getTeams();
		offensivePlayerService.refresh().then(function(resp) {
			self.players = offensivePlayerService.getPlayers();
		})
	})
	
	self.showPlayer = function(player,number) {
		offensivePlayerService.getSeasonStats(player).then(function(resp) {
			self.playerStats[number] = resp;
			self.playerStats[number].vis = true;
			offensivePlayerService.getAvgScore(player,3).then(function(resp) {
				self.playerStats[number].avgScore = resp;
				if (self.playerStats['1'].avgScore != 0.0 && self.playerStats['2'].avgScore != 0.0) {
					self.start.vis = true;
					self.getStart(self.playerStats['1'].avgScore,self.playerStats['2'].avgScore);
				}
				var currPlayer = offensivePlayerService.getPlayer(player);
				self.playerStats[number].name = currPlayer.name;
				self.playerStats[number].team = currPlayer.team.teamName;
				self.playerStats[number].pos = currPlayer.position.code;
			});
		});
	}
	
	self.getStart = function(score1,score2) {
		if(score1>=score2) {
			self.start['1'] = true;
			self.start['2'] = false;			
		} else {
			self.start['2'] = true;
			self.start['1'] = false;
		}
	}
	
	self.hideStats = function(number) {
		self.playerStats[number].vis = false;
		self.start = {vis: false, "1": false, "2": false};
	}
}]);