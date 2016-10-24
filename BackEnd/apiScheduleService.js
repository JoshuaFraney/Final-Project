mod.factory("apiScheduleService", ["$http","$q","token", function($http,$q,token) {
	var self = this;
	
	var scheduleInfo = [];
	var updMatchups = [];
	var updGameResults = [];
	var apiGameIdList = [];
	
	return {
		getScheduleInfo: function() {
			return scheduleInfo;
		},
		getUpdMatchups: function() {
			return updMatchups;
		},
		getUpdGameResults: function() {
			return updGameResults;
		},
		getApiGameIdList: function() {
			return apiGameIdList;
		},
		refresh: function() {
			var deferred = $q.defer();
			$http({
				url: "https://profootballapi.com/schedule",
				method: "POST",
				data: {
					api_key: token.getToken(),
					year: 2016,
					season_type: "REG"
				}
			}).success(function(resp) {
				scheduleInfo = resp;
				for(var game of scheduleInfo) {
					if(game.final) {apiGameIdList.push({id: game.id, vis: true, stats: {vis: false}});}
					var match = {
						week: game.week,
						homeTeam: game.home,
						awayTeam: game.away,
						isFinal: (game.final==1)?true:false,
						vis: true						
					};
					updMatchups.push(match);
					if(match.isFinal) {
						var isTie = true;
						var winner = game.home;
						var loser = game.away;
						var winScore = game.home_score;
						var loseScore = game.away_score;
						if(game.away_score > game.home_score) {
							winner = game.away;
							winScore = game.away_score;
							loser = game.home;
							loseScore = game.home_score;
							isTie = false;
						} else if(game.away_score < game.home_score) {
							isTie =false;
						}
						updGameResults.push({
							matchup: game.home + game.away + game.week,
							winTeam: winner,
							loseTeam: loser,
							winScore: winScore,
							loseScore: loseScore,
							tie: isTie,
							vis: true
						});
					}
				}
				deferred.resolve(resp);
			})
			return deferred.promise;
		}
	}
}]);
