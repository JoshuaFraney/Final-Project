mod.controller("adminCtrl",["$http","helper","token", function($http,helper,token) {
	var self = this;
		
	$http({
		url: "https://profootballapi.com/schedule",
		method: "POST",
		data: {
			api_key: token.getToken(),
			year: 2016,
			season_type: "REG"
		}
	}).then(function(resp) {
		console.log(resp);
		for(var matchup of resp.data)
		{
			var data = {
				week: matchup.week,
				isFinal: (matchup.final==1)?true:false,
			};
			$http({
				url: "http://localhost:8080/team/abrev/'" + matchup.home + "'",
				method: "GET"
			}).then(function(homeResp) {
				data.homeTeam = homeResp.data;
				$http({
					url: "http://localhost:8080/team/abrev/'" + matchup.home + "'",
					method: "GET"
				}).then(function(awayResp) {
					data.awayTeam = awayResp.data;
					$http({ 
						url: "http://localhost:8080/matchup/add",
						method: "POST",
						data: JSON.stringify(data),
						crossorigin: true,
						dataType: "json",
						contentType: "application/json"
					}).then(function(resp) {
						console.log(resp);
					})
				})
			})

		}
	});
}]);