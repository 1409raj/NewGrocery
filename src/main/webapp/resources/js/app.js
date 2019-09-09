var app = angular.module("myApp", ["ngRoute"]);


app.filter('secondsToMinute', function(){

	return function(timeInSeconds){
	
		if(isNaN(timeInSeconds)){
			return 'bad time. Enter time in seconds';
		}else{
			var minuteValue = parseInt(timeInSeconds/60);
			var secondsValue = timeInSeconds%60;
		}
		
		return minuteValue + ' min, ' + secondsValue + ' sec';
	
	}

});


app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "dashboard",
        controller : "dashboardCtrl"
    })
    
    .when("/main", {
        templateUrl : "main",
        controller : "mainCtrl"
    })
});
app.controller("dashboardCtrl", function ($scope, $http) {
  
});

