package com.weblib.dao;

import java.util.List;

import com.weblib.hbm.model.Publisher;

public class PublisherDAOImpl extends GenericDAOImpl<Publisher, Integer> {

	public Publisher insertPublisher(Publisher publisher) {
		insert(publisher);
		return publisher;
	}
	
	public Publisher findPublisherById(Integer id) {
		return findById(id);
	}
	
	public List<Publisher> findAllPublishers() {
		return findAll();
	}

	public Publisher findByName(String name) {
		return findByString("publisherName", name);
	}
	
}
