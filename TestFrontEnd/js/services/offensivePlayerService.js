mod.factory("offensivePlayerService", ["$http","$q","teamService","positionService", function($http,$q,teamService,positionService) {
	var self = this;
	
	var players = [];
	
	return {
		addPlayer: function(player) {
			var tempPlayer = {
				name: player.name,
				team: teamService.getTeam(player.team),
				position: positionService.getPosition(player.position)
			}
			var deferred = $q.defer();
			$http({
				url: "http://localhost:8080/offensivePlayer/add",
				method: "POST",
				data: tempPlayer
			}).success(function(resp) {
				deferred.resolve(resp);
			})
			return deferred.promise;
		},
		addPlayerStats: function(player,stats) {
			console.log("player",player);
			console.log("stats",stats);
			var tempStats = {
				player: player,
				opponent: stats.opponent,
				passAttempts: stats. passAttempts,
				passCompletions: stats.passCompletions,
				passYards: stats.passYards,
				passTouchdowns: stats.passTouchdowns,
				passInterceptions: stats.passInterceptions,
				receptions: stats.receptions,
				recYards: stats.recYards,
				recTouchdowns: stats.recTouchdowns,
				rushAttempts: stats.rushAttempts,
				rushYards: stats.rushYards,
				rushTouchdowns: stats.rushTouchdowns,
				fumbles: stats.fumbles
			};
			var deferred = $q.defer();
			$http({
				url: "http://localhost:8080/offensiveStat/add",
				method: "POST",
				data: tempStats
			}).success(function(resp) {
				deferred.resolve(resp);
			})
			return deferred.promise;
		},
		refresh: function() {
			var deferred = $q.defer();
			
			$http({
				url: "http://localhost:8080/offensivePlayer",
				method: "GET"
			}).success(function(resp) {
				players = resp;
				deferred.resolve(resp);
			})
			return deferred.promise;
		},
		getPlayers: function() {
			return players;
		}
	}
}]);