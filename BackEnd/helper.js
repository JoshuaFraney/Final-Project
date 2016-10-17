mod.factory("helper", function() {
	var self = this;
	self.week = 1;
	self.gameId = 0;

	return {
		getWeek: function() {
			return self.week;
		},
		setWeek: function(newWeek) {
			self.week = newWeek;
		},
		getGameId: function() {
			return self.gameId;
		},
		setGameId: function(newGameId) {
			self.gameId = newGameId;
		}
	}
});
