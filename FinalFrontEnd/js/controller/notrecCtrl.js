mod.controller("notrecCtrl", ["recPlayerService", function(recPlayerService) {
	var self = this;
	
	var bool = false;
	recPlayerService.refresh(3,bool,6,12,12,6).then(function(resp) {
		self.players = recPlayerService.getPlayers();
		console.log("Players",self.players);
	});
}]);