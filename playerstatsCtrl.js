// mod.controller("playerCtrl", ["$http","helper","token", function($http,helper,token) {
// 	var self=this;
// 	self.offensive_players = null;

	
// 	$http({
// 		url: "http://localhost:8080/offensive_player",
// 		method: "GET",
		

// 	}).then(function(resp) {
// 		console.log(resp);
// 		self.playername = resp.data;
// 	});
// }]);

mod.controller("playerstatsCtrl", ["$http","helper","token", function($http,helper,token) {
	var self=this;
	self.passAttempts = null;
	self.passCompletions= null;
	self.passYards = null;
	self.passTouchdowns = null;
	self.passInterceptions = null;
	self.receptions = null;
	self.recYards = null;
	self.recTouchdowns = null;
	self.fumbles = null;
	
	

	$http({
		url: "https://profootballapi.com/game",
		method: "POST",
		data: {
			api_key: token.getToken(),
			game_id: helper.getGameId()
		}
	}).then(function(resp) {
		console.log(resp);
		self.passAttempts = resp.data.stats.passAttempts;
		self.passCompletions = resp.data.stats.passCompletions;
		self.passYards = resp.data.stats.passYards;
		self.passTouchdowns = resp.data.stats.passTouchdowns;
		self.passInterceptions = resp.data.stats.passInterceptions;
		self.receptions = resp.data.stats.receptions;
		self.recYards = resp.data.stats.recYards;
		self.recTouchdowns = resp.data.stats.recTouchdowns;
		self.fumbles = resp.data.stats.fumbles;

	});
}]);