function SmartGranja(){}

SmartGranja.prototype.getLeituraSensores = function () {
var result;
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/SmartGranjaWeb3/getleiturasensor",
			success : function(data) {
				result = data;
				
			},
			error : function(data) {
				console.log(data);
			}
		});
		
		return result;
	}