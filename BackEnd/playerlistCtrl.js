mod.controller("playerlistCtrl", ["$http","$routeParams","teamService","offensivePlayerService","positionService", "helper","token", function($http,$routeParams,teamService,offensivePlayerService,positionService,helper,token) {
	var self=this;
	self.teams = null;
	self.team = $routeParams.team;


	positionService.refresh().then(function(resp) {
		self.positions = resp;
	});

	//Pull Team information from local db
	teamService.refresh().then(function(resp) {
		self.teams = teamService.getTeams();
		
		offensivePlayerService.refresh().then(function(resp) {
			self.players = offensivePlayerService.getPlayers();
		})
	});

	$http({
		url: "http://localhost:8080/offensivePlayer/byTeam",
		method: "GET",

	}).then(function(resp) {
		console.log(resp);
		self.byTeam = resp.data;
		// self.position.description = resp.data;
		self.roster = self.byTeam[self.team];
		});

function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

function filterFunction() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    div = document.getElementById("myDropdown");
    a = div.getElementsByTagName("a");
    for (i = 0; i < a.length; i++) {
        if (a[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
            a[i].style.display = "";
        } else {
            a[i].style.display = "none";
        }
    }
}

}]);