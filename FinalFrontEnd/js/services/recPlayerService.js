mod.factory("recPlayerService",["$http","$q",function($http,$q) {
	var self = this;
	
	var players = [];
	
	return {
		refresh: function(score,top,qbs,wrs,rbs,tes) {
			var deferred = $q.defer();
			$http({
				url: "http://localhost:8080/offensivePlayer/getRanking/score/" + score + 
				"/top/" + top + "/qb/" + qbs + "/wr/" + wrs + "/rb/" + rbs + "/te/" + tes,
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
	};
}]);