var myApp = angular.module('myApp', ['blockUI']).config(function (blockUIConfig) {

    blockUIConfig.message = 'Отравка писем...';
});

myApp.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

myApp.controller('myCtrl', ['$scope', '$http', function ($scope, $http) {

    $scope.sendMails = function () {
        $scope.sendResult = {};
        var fd = new FormData();
        fd.append('file', $scope.mailFile);
        fd.append('from', $scope.mailFrom);
        fd.append('to', $scope.mailTo.split(/\n/));
        fd.append('subject', $scope.subject);
        fd.append('text', $scope.mailText);
        $http.post('/mail/send', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
            .success(function (data) {
                $scope.sendResult = data;
            })
            .error(function () {
            });
    };
}]);