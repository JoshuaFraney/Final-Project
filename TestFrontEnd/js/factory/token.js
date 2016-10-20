mod.factory("token", function() {
	var self = this;
	self.token = "ENTER TOKEN HERE";
	
	return {
		getToken: function() {
			return self.token;
		}
	}
});