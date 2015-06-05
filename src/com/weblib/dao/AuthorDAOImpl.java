package com.weblib.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.weblib.hbm.model.Author;
import com.weblib.hbm.model.Book;

public class AuthorDAOImpl extends GenericDAOImpl<Author, Integer> {
	
	public Author insertAuthor(Author author) {
		insert(author);
		return author;
	}
	//	
	//	public List<Author> insertAuthors(List authors) {
	//		dao.insertAll(authors);
	//		return authors;
	//	}

	public List<Author> findAllAuthors() {
		return findAll();
	}

	public Author findAuthorByName(String name) {
		return findByString("authorName", name);
	}

	public Author findAuthorById(Integer id) {
		return findById(id);
	}

	public Set<Book> findBooksByAuthor(String authorName) {
		Author author = findByString("authorName", authorName);
		if (author != null) {
			return author.getBooks();
		} 

		return new HashSet<Book>();
	}

	public Set<Book> findBooksByAuthor(Integer id) {
		Author author = findAuthorById(id);
		if (author != null) {
			return author.getBooks();
		} 

		return new HashSet<Book>();
	}


}
