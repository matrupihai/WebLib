package com.weblib.services;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.weblib.dao.AuthorDAOImpl;
import com.weblib.json.JsonUtil;

@Path ("/search")
public class SearchResource {
	
	private AuthorDAOImpl authorDAO = new AuthorDAOImpl(); 
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response searchText(@QueryParam ("q") String q) {
		Set<String> authors = authorDAO.searchAuthor(q);
		return Response.ok(JsonUtil.objectToJson(authors)).build();
	}
	
}
