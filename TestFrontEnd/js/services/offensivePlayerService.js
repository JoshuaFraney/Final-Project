mod.factory("offensivePlayerService", ["$http","$q","teamService","positionService","token", function($http,$q,teamService,positionService,token) {
	var self = this;
	
	var players = [];
	var playersMap = {};
	
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
		addPlayerStats: function(stats) {
			console.log("stats",stats);
			var deferred = $q.defer();
			$http({
				url: "http://localhost:8080/offensiveStat/add",
				method: "POST",
				data: stats
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
				for(var player of players) {
					playersMap[player.team.abrev + player.name] = player;
					player.vis = true;
				}
				deferred.resolve(resp);
			})
			return deferred.promise;
		},
		getPlayers: function() {
			return players;
		},
		getPlayer: function(code) {
			return playersMap[code];
		},
		getStats: function(player) {
			var deferred = $q.defer();
			
			$http({
				url: "https://profootballapi.com/players",
				method: "POST",
				data: {
					api_key: token.getToken(),
					year: 2016,
					season_type: "REG",
					player_name: player.name,
					team: player.team.abrev,
					stats_type: "offense"
				}
			}).success(function(resp) {
				var stats = [];
				_.each(resp, function(value,key) {
					var apiGameId = key;
					_.each(value, function(value,key) {
						var gameStats = {
							passAttempts: 0,
							passCompletions: 0,
							passYards: 0,
							passTouchdowns: 0,
							passInterceptions: 0,
							receptions: 0,
							recYards: 0,
							recTouchdowns: 0,
							rushAttempts: 0,
							rushYards: 0,
							rushTouchdowns: 0,
							fumbles: 0,
							player: player,
							opponent: apiGameId
						}
						if(_.has(value,"passing")) {
							gameStats.passAttempts += value.passing.attempts;
							gameStats.passCompletions += value.passing.completions;
							gameStats.passYards += value.passing.yards;
							gameStats.passTouchdowns += value.passing.touchdowns;
							gameStats.passInterceptions += value.passing.interceptions;
						}
						if(_.has(value,"receiving")) {
							gameStats.receptions += value.receiving.receptions;
							gameStats.recYards += value.receiving.yards;
							gameStats.recTouchdowns += value.receiving.touchdowns;
							
						}
						if(_.has(value,"rushing")) {
							gameStats.rushAttempts += value.rushing.attempts;
							gameStats.rushYards += value.rushing.yards;
							gameStats.rushTouchdowns += value.rushing.touchdowns;
						}
						if(_.has(value,"fumbles")) {
							gameStats.fumbles += value.fumbles.fumbles_lost;
						}
						stats.push(gameStats);
					});
				});
				deferred.resolve(stats);
			})
			return deferred.promise;
		}
	}
}]);