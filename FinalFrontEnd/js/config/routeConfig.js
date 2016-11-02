mod.config(['$routeProvider', function($routeProvider) {

	$routeProvider
	.when('/week', {
		templateUrl: 'views/week.view.html',
		controller: 'weekCtrl',
		controllerAs: 'ctrl'
	}).when('/player', {
		templateUrl: 'views/player.view.html',
		controller: 'playerCtrl',
		controllerAs: 'ctrl'
	}).when('/rec', {
		templateUrl: 'views/rec.view.html',
		controller: 'recCtrl',
		controllerAs: 'ctrl'
	}).when('/notrec', {
		templateUrl: 'views/rec.view.html',
		controller: 'notrecCtrl',
		controllerAs: 'ctrl'
	}).when('/compare', {
		templateUrl: 'views/comp.view.html',
		controller: 'playerCompCtrl',
		controllerAs: 'ctrl'
	}).when('/roster', {
		templateUrl: 'views/playerlist.view.html',
		controller: 'playerlistCtrl',
		controllerAs: 'ctrl'
	})
	.otherwise({redirectTo: '/week'});
}]);