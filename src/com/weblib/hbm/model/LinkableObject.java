package com.weblib.hbm.model;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;

public abstract class LinkableObject {
	@JsonProperty
	protected Set<Link> links = new HashSet<Link>();
	
	public void addLink(Link link) {
		links.add(link);
	}
	
	public void addLink(String link, String rel) {
		Link newLink = new Link(link, rel);
		links.add(newLink);
	}
	
	public void addSelfLink(String link) {
		Link newLink = new Link(link, "self");
		links.add(newLink);
	}
}
