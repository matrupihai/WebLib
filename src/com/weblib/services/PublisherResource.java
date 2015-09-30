package com.weblib.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.weblib.dao.PublisherDAOImpl;

@Path ("/publishers")
public class PublisherResource {
	PublisherDAOImpl dao = new PublisherDAOImpl();
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getAllPublishers() {
		return Response.ok(dao.findAllPublishers()).build();
	}
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	@Path ("{id}")
	public Response getPublisherById(@PathParam ("id") Integer id) {
		return Response.ok(dao.findPublisherById(id)).build(); 
	}
	
}
