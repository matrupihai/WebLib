package com.weblib.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.weblib.dao.SubjectDAOImpl;

@Path ("/subjects")
public class SubjectResource {
	SubjectDAOImpl dao = new SubjectDAOImpl();
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getAllSubjects(@QueryParam ("subjectName") String subjectName) {
		if (subjectName != null) {
			return Response.ok(dao.findSubjectByName(subjectName)).build();
		}
		return Response.ok(dao.findAllSubjects()).build();
		
	}
	
	@GET
	@Path ("{id}")
	@Produces (MediaType.APPLICATION_JSON)
	public Response getSubjectById(@PathParam ("id") Integer id) {
		return Response.ok(dao.findSubjectById(id)).build();
	}
	
	@GET
	@Path ("{id}/books")
	@Produces (MediaType.APPLICATION_JSON)
	public Response getBooksBySubject(@PathParam ("id") Integer id) {
		return Response.ok(dao.findBooksBySubject(id)).build();
	}
	
}
