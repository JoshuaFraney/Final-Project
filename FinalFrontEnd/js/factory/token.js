mod.factory("token", function() {
	var self = this;
	self.token = "***********";
	self.api_key = "**************";
	
	return {
		getToken: function() {
			return self.token;
		},
		getApiKey: function() {
			return self.api_key;
		}
	}
});