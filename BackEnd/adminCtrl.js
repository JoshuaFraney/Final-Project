mod.controller("weekCtrl", ["$http","$location","helper","token", function($http,$location,helper,token) {
	var self = this;
	self.week = helper.getWeek();
	self.games = null;

	$http({
		url: "https://profootballapi.com/schedule",
		method: "POST",
		data: {
			api_key: token.getToken(),
			stats_type: "offense",
			year: 2016,
			season_type: "REG"
		}

	}).then(function(resp) {
		console.log(resp);
		self.games = resp.data;
		self.updWeek()
	});

	self.updWeek = function() {
		var week = $("#curr_week").val();
		console.log("old: ",helper.getWeek());
		console.log("new: ",week);
		helper.setWeek(week);
		for(var game of self.games) {
			if (game.week == week) {game.vis = true;} else {game.vis = false;}
		}
	}

	self.showPlayers = function(gameId) {
		helper.setGameId(gameId);
		$location.path("/player");
	}

}]);
