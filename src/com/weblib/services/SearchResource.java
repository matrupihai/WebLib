package com.weblib.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.weblib.dao.SearchDAOImpl;

@Path ("/search")
public class SearchResource {
	
	private SearchDAOImpl dao = new SearchDAOImpl();
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response searchText() {
		return null;
	}
	
}
