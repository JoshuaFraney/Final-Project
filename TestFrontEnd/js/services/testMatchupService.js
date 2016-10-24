mod.factory("testMatchupService", ["$http","$q", function($http,$q) {
	var self = this;
	
	return {
		testAdd: function() {
			var deferred = $q.defer();
			
			$http({
				url: "http://localhost:8080/matchup/add",
				method: "POST",
				data: {
					matchup: {
						week: 18,
						"final": true
					},
					homeTeam: "BAL",
					awayTeam: "BAL"
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
}])