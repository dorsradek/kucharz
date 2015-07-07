angular.module('product', []).controller('product', function($scope, $http) {

	$scope.isEdit = false;
	$scope.isAdd = false;
	$scope.isShow = false;

	$scope.fetchMiaryProduktuList = function() {
		$http.get('/product/miary').success(function(miarylist) {
			$scope.miaryProduktu = miarylist;
			$scope.miara = $scope.miaryProduktu[0].mipId;
		});
	};

	$scope.fetchRodzajeProduktuList = function() {
		$http.get('/product/rodzaje').success(function(rodzajelist) {
			$scope.rodzajeProduktu = rodzajelist;
			$scope.rodzaj = $scope.rodzajeProduktu[0].rdpId;
		});
	};

	$scope.fetchProductsList = function() {
		$scope.isEdit = false;
		$scope.isAdd = false;
		$scope.isShow = false;
		$http.get('/product/productlist.json').success(function(productlist) {
			$scope.products = productlist;
			$scope.product = {};
			if ($scope.miaryProduktu != undefined) {
				$scope.miara = $scope.miaryProduktu[0].mipId;
			}
			if ($scope.rodzajeProduktu != undefined) {
				$scope.rodzaj = $scope.rodzajeProduktu[0].rdpId;
			}
		});
	};
	
	$scope.startShowProduct = function(prd) {
		$scope.isEdit = false;
		$scope.isAdd = false;
		$scope.isShow = true;
		$http.get('/product/find/' + prd.prdId).success(function(product) {
			$scope.product = product;
			$http.get('/product/findMiara/' + prd.prdId).success(function(miara) {
				$scope.miaraShow = miara.mipNazwa;
			});
			$http.get('/product/findRodzaj/' + prd.prdId).success(function(rodzaj) {
				$scope.rodzajShow = rodzaj.rdpNazwa;
			});
		});
	}
	
	$scope.startAddProduct = function() {
		$scope.fetchProductsList();
		$scope.isEdit = false;
		$scope.isAdd = true;
		$scope.isShow = false;
	}

	$scope.startEditProduct = function(prd) {
		$scope.isEdit = true;
		$scope.isAdd = false;
		$scope.isShow = false;
		$http.get('/product/find/' + prd.prdId).success(function(product) {
			$scope.product = product;
			$http.get('/product/findMiara/' + prd.prdId).success(function(miara) {
				$scope.miara = miara.mipId;
			});
			$http.get('/product/findRodzaj/' + prd.prdId).success(function(rodzaj) {
				$scope.rodzaj = rodzaj.rdpId;
			});
		});
	}

	$scope.cancelProduct = function() {
		$scope.fetchProductsList();
	}

	$scope.addProduct = function() {
		$scope.parseDataFromForm();
		$http({
			method : 'POST',
			url : '/product/addProduct',
			data : $scope.product
		}).success(function() {
			$scope.fetchProductsList();
		});
	};

	$scope.removeProduct = function(prd) {
		$http({
			method : 'POST',
			url : '/product/removeProduct',
			data : prd
		}).success(function() {
			$scope.fetchProductsList();
		});
	};

	$scope.editProduct = function() {
		$scope.parseDataFromForm();
		$http({
			method : 'POST',
			url : '/product/editProduct',
			data : $scope.product
		}).success(function() {
			$scope.fetchProductsList();
		});
	};

	$scope.parseDataFromForm = function() {
		$scope.product.miaraProduktu = {};
		$scope.product.rodzajProduktu = {};
		$scope.product.miaraProduktu.mipId = $scope.miara;
		$scope.product.rodzajProduktu.rdpId = $scope.rodzaj;
	}

	$scope.fetchProductsList();
	$scope.fetchMiaryProduktuList();
	$scope.fetchRodzajeProduktuList();
});