mod.factory("slackService", ["$http","token", function($http,token) {
	var self = this;
	
	return {
		sendMsg: function(msg) {
			$http({
				url: "https://slack.com/api/chat.postMessage",
				method: "POST",
				data: {
					token: token.getSlackToken(),
					channel: "channel name",
					text: msg
				}
			});
		}
	}
}]);