mod.factory("matchupService", ["$http","$q", function($http,$q) {
	var self = this;
	
	var matchupsMap = {};
	var matchups = [];
	
	return {
		getMatchup: function(code) {
			return matchupsMap[code];
		},
		getMatchups: function() {
			return matchups;
		},
		refresh: function(updMatchups) {
			var deferred = $q.defer();
			$http({
				url: "http://localhost:8080/matchup",
				method: "GET"
			}).success(function(resp) {
				matchups = resp;
				for(var match of matchups) {
					matchupsMap[match.homeTeam.abrev + match.awayTeam.abrev + match.week] = match;
					for(var updMatch of updMatchups) {
						if(updMatch.home==match.home && updMatch.away == match.away && updMatch.week == match.week) {
							updMatch.vis = false;
						}
					}
				}
				deferred.resolve(resp);
			})
			return deferred.promise;
		},
		addMatchup: function(home,away,week,isFinal) {
			var deferred = $q.defer();
			$http({
				url: "http://localhost:8080/matchup/add",
				method: "POST",
				data: {
					week: week,
					homeTeam: home,
					awayTeam: away,
					"final": isFinal
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