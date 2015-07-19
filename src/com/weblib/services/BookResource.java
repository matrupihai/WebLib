package com.weblib.services;

import java.net.URI;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.weblib.dao.BookDAOImpl;
import com.weblib.hbm.model.Author;
import com.weblib.hbm.model.Book;
import com.weblib.hbm.model.BookCopy;

@Path ("/books")
public class BookResource {
	@Context
	UriInfo uriInfo;
	
	BookDAOImpl dao = new BookDAOImpl();
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getAllBooks(@QueryParam ("title") String title) {
		if (title != null) {
			return Response.ok(dao.findBookByTitle(title)).build();
		}
		
		return Response.ok(dao.getAllBooks()).build();
	}
	
	@GET
	@Path ("{isbn}")
	@Produces (MediaType.APPLICATION_JSON)
	public Response getBookById(@PathParam("isbn") Integer isbn) {
		Book book = dao.findBookByIsbn(isbn);
		addAuthorsLinks(book);
		
		return Response.ok(book).build();
	}
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	@Path ("{isbn}/copies")
	public Response getBookCopies(@PathParam("isbn") Integer isbn) {
		Set<BookCopy> result = dao.findBookCopies(isbn);
		return Response.ok(result).header("result-count", result.size()).build();
	}
	
	private void addAuthorsLinks(Book book) {
		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		for (Author author : book.getAuthors()) {
			URI authorUri = ub.path(AuthorResource.class)
					.path(String.valueOf(author.getAuthorId()))
					.build();
			author.addLink(authorUri.toASCIIString(), "author");
		}
	}
}
