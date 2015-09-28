var searchUrl = "http://localhost:8080/WebLib/search";

$("document").ready(function() {
	$("#globalSearch").autocomplete({
		source: function (request, response) {
			getSearchResults(request, response);
		},
		minLength: 2
	});
});

function getSearchResults(request, response) {
	$.ajax({
		type: "GET",
		url: searchUrl + "?q=" + request.term,
		dataType: "json", 
		success: function(data) {
			response(data);
		},
		error: console.log("Something went wrong")
	});
}

function parseResults(data) {
	var results = [];
	if (data != null) {
		var newData = data == null ? [] : (data instanceof Array ? data : [data]);
		$.each(newData, function(i, result) {
			var result = JSON.stringify(result);
			results.push(result);
		});
	}
	console.log(results);
	return results;
}


