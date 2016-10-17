mod.controller("playerCtrl", ["$http","helper","token", function($http,helper,token) {
	var self=this;
	self.homePassing = null;
	self.homeRushing = null;
	self.homeReceiving = null;
	self.awayPassing = null;
	self.awayRushing = null;
	self.awayReceiving = null;
	
	$http({
		url: "https://profootballapi.com/game",
		method: "POST",
		data: {
			api_key: token.getToken(),
			game_id: helper.getGameId()
		}
	}).then(function(resp) {
		console.log(resp);
		self.homePassing = resp.data.home.stats.passing;
		self.homeRushing = resp.data.home.stats.rushing;
		self.homeReceiving = resp.data.home.stats.receiving;
		self.awayPassing = resp.data.away.stats.passing;
		self.awayRushing = resp.data.away.stats.rushing;
		self.awayReceiving = resp.data.away.stats.receiving;
	});
}]);