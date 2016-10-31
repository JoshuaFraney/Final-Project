mod.factory("positionService", ["$http","$q", function($http,$q) {
	var self = this;
	
	var positionsMap = {};
	var positions = [];
	
	return {
		getPosition: function(pos) {
			console.log("posMap",positionsMap);
			return positionsMap[pos];
		},
		getPositions: function() {
			return positions;
		},
		refresh: function() {
			var deferred = $q.defer();
			$http({
				url: "http://localhost:8080/position",
				method: "GET"
			}).success(function(resp) {
				positions = resp;
				for(var position of positions) {
					positionsMap[position.code] = position;
				}
				console.log("posMap",positionsMap);
				deferred.resolve(resp);
			})
			return deferred.promise;
		}
	}
}]);