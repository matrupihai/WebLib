var baseUrl = "http://localhost:8080/WebLib/";
var searchUrl = baseUrl + "search";
var booksUrl = baseUrl + "/books?title="


$("document").ready(function() {
	$("#globalSearch").autocomplete({
		source: function (request, response) {
			getSearchResults(request, response);
		},
		minLength: 3,
		delay: 500
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
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR.error);
		},
		select: function(event, ui) {
			findBooks(booksUrl + ui.item.value)
		}
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


