package com.weblib.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.weblib.hbm.model.Book;
import com.weblib.hbm.model.BookCopy;

public class BookDAOImpl extends GenericDAOImpl<Book, Integer> {

	public Book insertBook(Book entity) {
		super.insert(entity);
		return entity;
	}	

	public List<Book> getAllBooks() {
		return findAll();
	}

	public Book findBookByTitle(String title) {
		return findByString("title", title);
	}
	
	public Book findBookByIsbn(Integer isbn) {
		return findById(isbn);
	}

	public Set<BookCopy> findBookCopies(Integer isbn) {
		Book book = findBookByIsbn(isbn);
		if (book != null) {
			return book.getCopies();
		}

		return new HashSet<BookCopy>();
	}
	
	public Set<String> searchBook(String searchTerm) {
		Set<String> searchResults = new HashSet<>();
		Set<Book> books = genericFind("from " + getObjectType().getSimpleName() + " where title like '%" + searchTerm + "%'");
		
		for (Book book : books) {
			searchResults.add(book.getTitle());
		}
		
		return searchResults;
	}

}
