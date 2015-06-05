package com.weblib.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.weblib.dao.UserDAOImpl;

@Path("/users")
public class UserResource {
	UserDAOImpl dao = new UserDAOImpl();
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getAllUsers(@QueryParam ("userName") String userName) {
		if (userName != null) {
			return Response.ok(dao.findByName(userName)).build();
		}
		
		return Response.ok(dao.findAllUsers()).build();
	}
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	@Path ("{id}")
	public Response getUserById(@PathParam("id") Integer id) {
		return Response.ok(dao.findUserById(id)).build();
	}
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	@Path ("{id}/copies")
	public Response getBorrowedByUser(@PathParam("id") Integer id) {
		return Response.ok(dao.findBorrowedByUser(id)).build();
	}
	
}
