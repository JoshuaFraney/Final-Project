mod.factory("token", function() {
	var self = this;
	self.token = "VYE6MQ1GgjOv0oqeBJTSAKzZD7hUusLN";
	self.api_key = "AIzaSyDbOMeklgU0RuoqbpYNIjsnInOyOMNX3xo";
	
	return {
		getToken: function() {
			return self.token;
		},
		getApiKey: function() {
			return self.api_key;
		}
	}
});

