mod.factory("gameResultService", ["$http","$q", function($http,$q) {
	var self = this;
	
	var gameResults = [];
	
	return {
		getGameResults: function() {
			return gameResults;
		},
		refresh: function(updGameResults) {
			var deferred = $q.defer();
			$http({
				url: "http://localhost:8080/gameResult",
				method: "GET"
			}).success(function(resp) {
				gameResults = resp;
				for(var game of gameResults) {
					for(var updGame of updGameResults) {
						if(updGame.matchup == (game.matchup.homeTeam.abrev + game.matchup.awayTeam.abrev + game.matchup.week)) {
							updGame.vis = false;
						}
					}
				}
				deferred.resolve(resp);
			})
			return deferred.promise;
		},
		addResult: function(match,winner,loser,winScore,loseScore,tie) {
			var deferred = $q.defer();
			$http({
				url: "http://localhost:8080/gameResult/add",
				method: "POST",
				data: {
					matchup: match,
					winTeam: winner,
					loseTeam: loser,
					winScore: winScore,
					loseScore: loseScore,
					tie: tie
				},
				crossorigin: true,
				dataType: "json",
				contentType: "application/json"
			}).success(function(resp) {
				deferred.resolve(resp);
			})
			return deferred.promise;
		}
	}
}]);