var loremIpsum = "Random text here";
var baseUrl = "http://localhost:8080/WebLib/";
var searchUrl = baseUrl + "search";
var booksUrl = baseUrl + "books?title="


$("document").ready(function() {
	$("#globalSearch").autocomplete({
		source: function (request, response) {
			getSearchResults(request, response);
		},
		minLength: 3,
		delay: 500,
		select: function(event, ui) {
			var titleUrl = booksUrl + ui.item.value;
			findBooks(titleUrl);
		}
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

function findBooks(url) {
	console.log("findAllBooks");
	$.ajax({
		type: "GET",
		url: url,
		dataType: "json", 
		success: renderBooksList
	});
}

function renderBooksList(data) {
	var newData = data == null ? [] : (data instanceof Array ? data : [data]);
	$.each(newData, function(i, book) {
		var bookCard = renderBook(book);
		var jsonBook = JSON.stringify(book);
		jQuery.data(bookCard[0], "bookModel", jsonBook);
		findCopies(book, getBookCopies);
		$(".booksContainer").append(bookCard);
	});
}

function findCopies(book, renderBookCopies) {
	var bookIsbn = book.isbn;
	var copiesUrl = booksUrl + "/" + bookIsbn + "/copies";
	$.ajax({
		type: "GET",
		url: copiesUrl,
		dataType: "json",
		success: renderBookCopies
	});
}

function getBookCopies(data) {
	console.log(data.length);
}

function renderBook(book, findCopies) {
	var bookAuthors = [];

	$.each(book.authors, function(i, author) {
		bookAuthors.push(author.authorName);
	});
	
	var bookCard = $("<div/>", {
		class: "bookCard"
	});

	var bookHeader = $("<div/>", {
		class: "bookHeader"
	});

	var bookTitle = $("<h1/>", {
		class: "bookTitle",
		html: book.title
	});

	var bookAuthor = $("<p/>", {
		class: "bookAuthor",
		html: bookAuthors.toString()
	});

	var bookPages = $("<span/>", {
		class: "bookPages",
		html: book.noOfPages + " pages"
	});

	var bookImg = $("<div/>", {
		class: "bookImg",
		html: "<img alt=\"bookImg\" src=\"img/imgStub.png\" />",
	});

	var bookDescription = $("<p/>", {
		class: "bookDescription",
		html: loremIpsum
	});

	var bookActions = $("<div/>", {
		class: "bookActions"
	});

	var borrowBook = $("<button/>", {
		class: "borrowBook",
		type: "button",	
		html: "Borrow"
	});
	
	var bookCopies = $("<p/>", {
		class: "copies",
		html: "Available copies: "	
	});
	
	bookImg.appendTo(bookCard);
	bookHeader.appendTo(bookCard);
	bookTitle.appendTo(bookHeader);
	bookAuthor.appendTo(bookHeader);
	bookPages.appendTo(bookAuthor);
	bookDescription.appendTo(bookCard);
	bookActions.appendTo(bookCard);
	borrowBook.appendTo(bookActions);
	bookCopies.appendTo(bookActions);
	
	return bookCard;
}

$(document).on("click", ".borrowBook", function(event) {
	var bookCard = $(event.target).closest(".bookCard");
	var bookJson = JSON.parse(jQuery.data(bookCard[0], "bookModel"));
	console.log("Book (isbn: " + bookJson.isbn + ") borrowed!");
});


