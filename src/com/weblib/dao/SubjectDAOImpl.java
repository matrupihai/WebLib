package com.weblib.dao;

import java.util.ArrayList;
import java.util.List;

import com.weblib.hbm.model.Book;
import com.weblib.hbm.model.Subject;

public class SubjectDAOImpl extends GenericDAOImpl<Subject, Integer> {
	
	public List<Subject> findAllSubjects() {
		return findAll();
	}
	
	public Subject findSubjectById(Integer id) {
		return findById(id);
	}
	
	public Subject findSubjectByName(String subjectName) {
		return findByString("subjectName", subjectName);
	}
	
	public List<Book> findBooksBySubject(Integer id) {
		List<Book> books = new ArrayList<Book>();
		Subject subject = findSubjectById(id);
		if (subject != null) {
			books.addAll(subject.getBooks());
		}
		
		return books;
	}
	
}
