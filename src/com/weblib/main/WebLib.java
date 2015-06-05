package com.weblib.main;

import com.weblib.dao.BookDAOImpl;
import com.weblib.dao.PublisherDAOImpl;
import com.weblib.hbm.model.Author;
import com.weblib.hbm.model.Book;
import com.weblib.hbm.model.Publisher;

public class WebLib {
	
	public WebLib() {}
	
	
	public static void main(String[] args) {
//		findBooksByAuthor("osbuc");
	}
	
//	private static void insertAuthor(String name) {
//		Author author = new Author();
//		author.setAuthorName(name);
//		new AuthorDAOImpl().insertAuthor(author);
//	}
//	
//	private static void insertAuthors() {
//		List<Author> list = new ArrayList<Author>();
//		for (Author a : new AuthorDAOImpl().findAllAuthors()) {
//			a.setAuthorName(a.getAuthorName() + " TEST");
//			list.add(a);
//		}
//		
//		new AuthorDAOImpl().insertAuthors(list);
//	}
	
	private static void insertOneToMany() {
		Publisher p = new Publisher();
		p.setPublisherName("Penguin");
		
		Book b = new Book();
		b.setNoOfPages(354);
		b.setTitle("The Stranger");
		b.setYearPublished(1949);
		
		b.setPublisher(p);
		p.getBooks().add(b);
		
		new PublisherDAOImpl().insertPublisher(p);
	}
	
	private static void insertManyToMany() {
		Publisher p = new Publisher();
		p.setPublisherName("Humanitas");
		
		Author author = new Author();
		author.setAuthorName("Ion Creanga");
		
		Author authorTwo = new Author();
		authorTwo.setAuthorName("George Cosbuc");
		
		Book book = new Book();
		book.setTitle("Collab G and C");
		book.setNoOfPages(567);
		book.setYearPublished(1978);
		
		book.getAuthors().add(author);
		book.getAuthors().add(authorTwo);
		book.setPublisher(p);
		
		author.getBooks().add(book);
		authorTwo.getBooks().add(book);
		
		new BookDAOImpl().insert(book);
	}
	
//	private static void findBooksByAuthor(String authorName) {
//		Author author = new AuthorDAOImpl().findBooksByAuthor(authorName).get(0);
//		System.out.println("Author: " + author.getAuthorName());
//		System.out.println("\nBooks: " + author.getBooks());
//	}
}
