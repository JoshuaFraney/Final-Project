mod.controller("PlayersCtrl", [])

PlayersCtrl.$inject = ["Players"]
function PlayersCtrl(Players) {
  var self = this

  self.players = Players.all

};




// REFER to the already defined module
// angular.module("todos")
