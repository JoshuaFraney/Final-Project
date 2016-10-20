mod.factory("helper", function() {
	var self = this;
	self.week = 1;
	
	return {
		var getWeek = function() {
			return self.week;
		},
		var setWeek = function(newWeek) {
			self.week = newWeek;
		}
	}
});