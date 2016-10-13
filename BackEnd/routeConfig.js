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
	}).when('/admin',{
		templateUrl: 'views/admin.view.html',
		controller: 'adminCtrl',
		controllerAs: 'ctrl'
	})
	.otherwise({redirectTo: '/'});
}]);
