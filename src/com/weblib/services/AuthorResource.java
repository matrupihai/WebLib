package com.weblib.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.weblib.dao.AuthorDAOImpl;

@Path("/authors")
public class AuthorResource {
	AuthorDAOImpl dao = new AuthorDAOImpl();
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getAuthors(@QueryParam("authorName") String authorName) {
		if (authorName != null) {
			return Response.ok(dao.findAuthorByName(authorName)).build();
		}
		return Response.ok(dao.findAllAuthors()).build();
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
	
}
