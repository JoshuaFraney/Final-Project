mod.controller("adminCtrl",
	["$http","teamService","apiScheduleService","apiStatService","matchupService", "gameResultService",
	function($http,teamService,apiScheduleService,apiStatService,matchupService,gameResultService) {
	var self = this;
	
	//Pull Team information from local db
	teamService.refresh().then(function(resp) {
		self.teams = teamService.getTeams();
	
		//Pull schedule information from api
		apiScheduleService.refresh().then(function(resp) {
			self.scheduleInfo = apiScheduleService.getScheduleInfo();
			self.updMatchups = apiScheduleService.getUpdMatchups();
			self.updGameResults = apiScheduleService.getUpdGameResults();
			self.apiGameIdList = apiScheduleService.getApiGameIdList();
			
			//Pull matchup information from local db
			matchupService.refresh(self.updMatchups).then(function(resp) {
				self.matchups = matchupService.getMatchups();
			});
			
			//Pull result information from local db
			gameResultService.refresh(self.updGameResults).then(function(resp) {
				self.gameResults = gameResultService.getGameResults();
			});
		});
		
	});
	
	
	//Add Functions
	self.addMatchup = function(homeTeam,awayTeam,week,isFinal) {
		var home = teamService.getTeam(homeTeam);
		var away = teamService.getTeam(awayTeam);
		matchupService.addMatchup(home,away,week,isFinal);
	}
	
	self.addResult = function(matchup,winTeam,loseTeam,winScore,loseScore,tie) {
		var match = matchupService.getMatchup(matchup);
		var winner = teamService.getTeam(winTeam);
		var loser = teamService.getTeam(loseTeam);
		gameResultService.addResult(match,winner,loser,winScore,loseScore,tie).then(function(resp) {
			teamService.refresh();
		})
	}
	
	self.getGameStats = function(gameInfo) {
		apiStatService.getGameStats(gameInfo.id).then(function(resp) {
			console.log(resp);
			gameInfo.stats["defense"] = apiStatService.getDefense();
			gameInfo.stats["offense"] = apiStatService.getOffense();
			gameInfo.vis = false;
			gameInfo.stats.vis = true;
			console.log(gameInfo);
		});
		
	}

}]);