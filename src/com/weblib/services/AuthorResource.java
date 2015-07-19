package com.weblib.services;

import java.net.URI;
import java.util.List;

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

import com.weblib.dao.AuthorDAOImpl;
import com.weblib.hbm.model.Author;

@Path("/authors")
public class AuthorResource {
	@Context
	UriInfo uriInfo;
	
	AuthorDAOImpl dao = new AuthorDAOImpl();
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getAuthors(@QueryParam("authorName") String authorName) {
		if (authorName != null) {
			return Response.ok(dao.findAuthorByName(authorName)).build();
		}
		
		List<Author> allAuthors = dao.findAllAuthors();
		for (Author author : allAuthors) {
			addSelfLink(author);
		}
		
		return Response.ok(allAuthors).build();
	}

	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getAuthorById(@PathParam("id") Integer id) {
		return Response.ok(dao.findAuthorById(id)).build();
	}
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	@Path("{id}/books")
	public Response getBooksByAuthorId(@PathParam("id") Integer id) {
		return Response.ok(dao.findBooksByAuthor(id)).build();
	}
	
	private void addSelfLink(Author author) {
		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI authorUri = ub.path(String.valueOf(author.getAuthorId())).build();
		author.addSelfLink(authorUri.toASCIIString());
	}

}
