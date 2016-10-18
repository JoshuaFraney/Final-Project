// DEFINE the todo module
// var app = angular.module("Players", [])

// app.controller("TodosCtrl", ["$http", function($http) {
  // Logic goes here
// }])

mod.controller("PlayersCtrl", PlayersCtrl)

PlayersCtrl.$inject = ["$http"]
function PlayersCtrl($http) {
  var self = this

  self.players = [
    { name: "J.Flacco", teamId: 3, position: "Quarterback", posRanking: 20},
    { name: "M.Ryan", teamId: 2, position: "Quarterback", posRanking: 3},
    { name: "J.Nelson", teamId: 12, position: "Wide Receiver", posRanking: 17},
    { name: "J.Jones", teamId: 2, position: "Wide Receiver", posRanking: 1},
    { name: "O.Beckham", teamId: 22, position: "Wide Receiver", posRanking: 7},
    { name: "J.Edelman", teamId: 20, position: "Wide Receiver", posRanking: 14},
    { name: "J.Charles", teamId: 16, position: "Running back", posRanking: 2},
    { name: "C.Anderson", teamId: 10, position: "Running back", posRanking: 13},
    { name: "M.Forte", teamId: 23, position: "Running back", posRanking: 9},
    { name: "L.Miller", teamId: 13, position: "Running back", posRanking: 6},
    { name: "R.Gronkowski", teamId: 20, position: "Tight End", posRanking: 2},
    { name: "Delanie Walker", teamId: 31, position: "Tight End", posRanking: 5},
    { name: "J.Tucker", teamId: 3, position: "Kicker", posRanking: 2},
    { name: "D.Bailey", teamId: 9, position: "Kicker", posRanking: 4}

  ]

  // self.reverseList = function() {
  //   self.players.reverse()
  // }
}

// REFER to the already defined module
// angular.module("todos")
