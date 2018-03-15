var app = angular.module("stocksApi", ["ui.grid", "ui.grid.pagination","ui.grid.selection", "ui.grid.exporter", "ui.grid.resizeColumns"]);
    app.controller("stocksController", function ($scope, $http) {

    $scope.gridOptions = {
    enableFiltering: true,
    enableSorting: true,
    paginationPageSizes: [10, 100, 1000],
    paginationPageSize: 10,
    enableGridMenu: true,
    enableFullRowSelection:true,
    enableColumnResizing: true,
    exporterMenuPdf: true,
    columnDefs: [
        { field: 'NAME' , displayName: 'Name', width:'30%'},
        { field: 'CURRENTPRICE' , displayName: 'Current Price', width:'30%'},
        { field: 'LASTUPDATED', displayName: 'Last update', width:'40%'  }
        ],
    onRegisterApi: function (gridApi) {
    $scope.grid1Api = gridApi;
        }
    };
  $http.get('http://localhost:8086/payconiq/api/stocks').
        then(function(response) {
            $scope.stocks = response.data;
                $scope.gridOptions.data =$scope.stocks;

        });

    });
