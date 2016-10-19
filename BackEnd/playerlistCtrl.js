mod.controller("playerlistCtrl", ["$http","helper","token", function($http,helper,token) {
	var self=this;
	self.teams = null;

	
	$http({
		url: "https://profootballapi.com/game",
		method: "POST",
		data: {
			api_key: token.getToken(),
			year: 2016,
			season_type: REG
		}

	}).then(function(resp) {
		console.log(resp);
		self.teams = resp.data;
		console.log(self.teams)
	});
}]);