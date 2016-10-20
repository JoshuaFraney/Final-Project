mod.factory("apiStatService", ["$http","$q","token","teamService", function($http,$q,token,teamService) {
	var self = this;
	
	var gameId = 2016091100;
	var gameStats;
	
	return {
		getGameId: function() {
			return gameId;
		},
		getGameStats: function(game_id) {
			var deferred = $q.defer();
			gameId = game_id;
			$http({
				url: "https://profootballapi.com/game",
				method: "POST",
				data: {
					api_key: token.getToken(),
					game_id: game_id
				}
			}).success(function(resp) {
				gameStats = resp;
				deferred.resolve(resp);
			})
			return deferred.promise;
		},
		//Get team, points allowed, pass yards, rush yards, sacks, ints, and fumblRec
		getDefense: function() {
			var homeTeam = gameStats.home.team;
			var awayTeam = gameStats.away.team;
			
			var homePointsAllowed = gameStats.away_score;
			var awayPointsAllowed = gameStats.home_score;
			
			var homePassYardsAllowed = gameStats.away.pyds;
			var awayPassYardsAllowed = gameStats.home.pyds;
			
			var homeRushYardsAllowed = gameStats.away.ryds;
			var awayRushYardsAllowed = gameStats.home.ryds;
			
			var homeSacks = 0;
			var homeInts = 0;
			_.each(gameStats.home.stats.defense, function(value,key){
				homeSacks += value.sacks;
				homeInts += value.interceptions;
			});
			var homeFumblRec = gameStats.away.trnovr - homeInts;
			
			var awaySacks = 0;
			var awayInts = 0;
			_.each(gameStats.away.stats.defense, function(value,key) {
				awaySacks += value.sacks;
				awayInts += value.interceptions;
			});
			var awayFumblRec = gameStats.home.trnovr - awayInts;
			
			return {
				home: {
					passYardsAllowed: homePassYardsAllowed,
					rushYardsAllowed: homeRushYardsAllowed,
					team: teamService.getTeam(homeTeam),
					pointsAllowed: homePointsAllowed,
					sacks: homeSacks,
					interceptions: homeInts,
					fumbleRcvry: homeFumblRec
				},
				away: {
					passYardsAllowed: awayPassYardsAllowed,
					rushYardsAllowed: awayRushYardsAllowed,
					team: teamService.getTeam(awayTeam),
					pointsAllowed: awayPointsAllowed,
					sacks: awaySacks,
					interceptions: awayInts,
					fumbleRcvry: awayFumblRec
				}
			}
		},
		//Get passing, rushing, and recieving stats for all players
		/*
			{
				player: {
					name:
					team:
					position:
				}
				passAttempts:
				passCompletions:
				passYards:
				passTouchdowns:
				PassInterceptions:
				receptions:
				recYards:
				recTouchdowns:
				rushAttempts:
				rushYards:
				rushTouchdowns:
				fumbles:
				}
			}
		*/
		getOffense: function() {
			var stats = {
				home: {},
				away: {}
			};
			
			//Home Passing
			_.each(gameStats.home.stats.passing, function(value,key) {
				if(_.has(stats.home,value.name)) {
					stats.home[value.name].passAttempts += value.attempts;
					stats.home[value.name].passCompletions += value.completions;
					stats.home[value.name].passYards += value.yards;
					stats.home[value.name].passInterceptions += value.interceptions;
				} else {
					stats.home[value.name] = {
						name: value.name,
						passAttempts: value.attempts,
						passCompletions: value.completions,
						passYards: value.yards,
						passTouchdowns: value.touchdowns,
						passInterceptions: value.interceptions,
						receptions: 0,
						recYards: 0,
						recTouchdowns: 0,
						rushAttempts: 0,
						rushYards: 0,
						rushTouchdowns: 0,
						fumbles: 0
					}
				}
			});
			
			//Home Recieving
			_.each(gameStats.home.stats.receiving, function(value,key) {
				if(_.has(stats.home,value.name)) {
					stats.home[value.name].receptions += value.receptions;
					stats.home[value.name].recYards += value.yards;
					stats.home[value.name].recTouchdowns += value.touchdowns;
				} else {
					stats.home[value.name] = {
						name: value.name,
						passAttempts: 0,
						passCompletions: 0,
						passYards: 0,
						passTouchdowns: 0,
						passInterceptions: 0,
						receptions: value.receptions,
						recYards: value.yards,
						recTouchdowns: value.touchdowns,
						rushAttempts: 0,
						rushYards: 0,
						rushTouchdowns: 0,
						fumbles: 0
					}
				}
			});			
	
			//Home Rushing
			_.each(gameStats.home.stats.rushing, function(value,key) {
				if(_.has(stats.home,value.name)) {
					stats.home[value.name].rushAttempts += value.attempts;
					stats.home[value.name].rushYards += value.yards;
					stats.home[value.name].rushTouchdowns += value.touchdowns;
				} else {
					stats.home[value.name] = {
						name: value.name,
						passAttempts: 0,
						passCompletions: 0,
						passYards: 0,
						passTouchdowns: 0,
						passInterceptions: 0,
						receptions: 0,
						recYards: 0,
						recTouchdowns: 0,
						rushAttempts: value.attempts,
						rushYards: value.yards,
						rushTouchdowns: value.touchdowns,
						fumbles: 0
					}
				}
			});		

			//Away Passing
			_.each(gameStats.away.stats.passing, function(value,key) {
				if(_.has(stats.away,value.name)) {
					stats.away[value.name].passAttempts += value.attempts;
					stats.away[value.name].passCompletions += value.completions;
					stats.away[value.name].passYards += value.yards;
					stats.away[value.name].passInterceptions += value.interceptions;
				} else {
					stats.away[value.name] = {
						name: value.name,
						passAttempts: value.attempts,
						passCompletions: value.completions,
						passYards: value.yards,
						passTouchdowns: value.touchdowns,
						passInterceptions: value.interceptions,
						receptions: 0,
						recYards: 0,
						recTouchdowns: 0,
						rushAttempts: 0,
						rushYards: 0,
						rushTouchdowns: 0,
						fumbles: 0
					}
				}
			});
			
			//Away Recieving
			_.each(gameStats.away.stats.receiving, function(value,key) {
				if(_.has(stats.away,value.name)) {
					stats.away[value.name].receptions += value.receptions;
					stats.away[value.name].recYards += value.yards;
					stats.away[value.name].recTouchdowns += value.touchdowns;
				} else {
					stats.away[value.name] = {
						name: value.name,
						passAttempts: 0,
						passCompletions: 0,
						passYards: 0,
						passTouchdowns: 0,
						passInterceptions: 0,
						receptions: value.receptions,
						recYards: value.yards,
						recTouchdowns: value.touchdowns,
						rushAttempts: 0,
						rushYards: 0,
						rushTouchdowns: 0,
						fumbles: 0
					}
				}
			});			
	
			//Away Rushing
			_.each(gameStats.away.stats.rushing, function(value,key) {
				if(_.has(stats.away,value.name)) {
					stats.away[value.name].rushAttempts += value.attempts;
					stats.away[value.name].rushYards += value.yards;
					stats.away[value.name].rushTouchdowns += value.touchdowns;
				} else {
					stats.away[value.name] = {
						name: value.name,
						passAttempts: 0,
						passCompletions: 0,
						passYards: 0,
						passTouchdowns: 0,
						passInterceptions: 0,
						receptions: 0,
						recYards: 0,
						recTouchdowns: 0,
						rushAttempts: value.attempts,
						rushYards: value.yards,
						rushTouchdowns: value.touchdowns,
						fumbles: 0
					}
				}
			});				
			
			return stats;
		}
	}
}]);