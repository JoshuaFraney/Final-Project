mod.config(['$routeProvider', function($routeProvider) {

	$routeProvider
	.when('/', {
		templateUrl: 'views/home.view.html'

	}).when('/week', {
		templateUrl: 'views/week.view.html',
		controller: 'weekCtrl',
		controllerAs: 'ctrl'

	}).when('/about', {
		templateUrl: 'views/about.view.html'

	}).when('/player', {
		templateUrl: 'views/player.view.html',
		controller: 'playerCtrl',
		controllerAs: 'ctrl'

	}).when('/players', {
		templateUrl: 'views/playerstats.view.html',
		controller: 'PlayersCtrl',
		controllerAs: 'ctrl'

	}).when('/admin',{
		templateUrl: 'views/admin.view.html',
		controller: 'adminCtrl',
		controllerAs: 'ctrl'

	}).when('/playerlist',{
		templateUrl: 'views/playerlist.view.html',
		controller: 'playerlistCtrl',
		controllerAs: 'ctrl'

	}).when('/reccommended',{
		templateUrl: 'views/reccommended.view.html',
		controller: 'reccommendedCtrl',
		controllerAs: 'ctrl'
	
	}).when('/dontplay',{
		templateUrl: 'views/dontplay.view.html',
		controller: 'dontplayCtrl',
		controllerAs: 'ctrl'

	}).when('/compare',{
		templateUrl: 'views/compare.view.html',
		controller: 'compareCtrl',
		controllerAs: 'ctrl'
	})

	.otherwise({redirectTo: '/'});
}]);