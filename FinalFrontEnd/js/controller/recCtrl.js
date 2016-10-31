mod.controller("recCtrl", ["recPlayerService", function(recPlayerService) {
	var self = this;
	
	var bool = true;
	recPlayerService.refresh(3,bool,6,12,12,6).then(function(resp) {
		self.players = recPlayerService.getPlayers();
		console.log("Players",self.players);
	});
}]);