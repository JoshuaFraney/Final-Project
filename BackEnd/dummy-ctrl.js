angular
  .module("players")
  .controller("PlayersCtrl", PlayersCtrl)

PlayersCtrl.$inject = ["Players"]
function PlayersCtrl(Players) {
  var self = this

  self.players = Players.all


//   self.reverseList = function() {
//     self.todos.reverse()
//   }
//
//   self.markComplete = function(item) {
//     item.complete = true
//   }
//
//   self.createItem = function() {
//     console.log("should save", self.newName)
//
//     Items.create(self.newName)
//
//     self.newName = ""
//   }
// }









// REFER to the already defined module
// angular.module("todos")
