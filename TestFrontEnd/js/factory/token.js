mod.factory("token", function() {
	var self = this;
	self.token = "*****************";
	
	return {
		getToken: function() {
			return self.token;
		}
	}
});