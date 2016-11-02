mod.controller("weekCtrl", ["$http","$location","helper","token","teamService", function($http,$location,helper,token,teamService) {
	var self = this;
	self.week = helper.getWeek();
	self.games = [];
	
	self.getTeam = function(code) {
		return teamService.getTeam(code);
	}
	
	teamService.refresh().then(function() {
		$http({
			url: "https://profootballapi.com/schedule",
			method: "POST",
			data: {
				api_key: token.getToken(),
				stats_type: "offense",
				year: 2016,
				season_type: "REG"
			}
			
		}).then(function(resp) {
			console.log(resp);
			for(game of resp.data) {
				if (game.final==1) {
					self.games.push(game);
				}
			}
			//self.games = resp.data;
		});
	});
		
	self.showPlayers = function(gameId) {
		helper.setGameId(gameId);
		helper.setWeek(self.week);
		$location.path("/player");
	}
		
}]);