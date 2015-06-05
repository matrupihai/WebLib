
var booksUrl = "http://localhost:8080/WebLib/books";
var loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
"Suspendisse dapibus cursus eros. Nunc euismod nisl sed dapibus feugiat. " +
"Nullam at metus et turpis dictumelementum.";

$("document").ready(
	findAllBooks()
);

function findAllBooks() {
	console.log("findAllBooks");
	$.ajax({
		type: "GET",
		url: booksUrl,
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


