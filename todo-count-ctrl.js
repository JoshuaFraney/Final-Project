angular
  .module("todos")
  .controller("TodoCountCtrl", TodoCountCtrl)

TodoCountCtrl.$inject = ["Items"]
function TodoCountCtrl(Items) {
  var self = this

  self.items = Items.all

  self.count = function() {
    return self.items.length
  }

  self.progressBarPercent = Items.progress() * 100
}
