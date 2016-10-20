mod.factory("teamService", ["$http","$q", function($http,$q) {
	var self = this;
	
	var teamsMap = {};
	var teams = [];
	
	return {
		getTeam: function(code) {
			return teamsMap[code];
		},
		getTeams: function() {
			return teams;
		},
		refresh: function() {
			var deferred = $q.defer();
			$http({
				url: "http://localhost:8080/team",
				method: "GET"
			}).success(function(resp) {
				teams = resp;
				for(var team of teams) {
					teamsMap[team.abrev] = team;
				}
				deferred.resolve(resp);
			})
			return deferred.promise;
		}
	}
}]);