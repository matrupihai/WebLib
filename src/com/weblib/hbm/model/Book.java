package com.weblib.hbm.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;


@Entity
@Table (name="DBA.\"102_BOOKS\"")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book extends LinkableObject {
	@Id
	@Column (name = "isbn")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int isbn;
	
	@Column (name = "title")
	private String title;
	
	@Column (name = "no_of_pages")
	private int noOfPages;
	
	@Column (name = "year_published")
	private int yearPublished;
	
	@JsonIgnore
	@ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn (name = "publisher_id")
	private Publisher publisher;
	
	@JsonManagedReference
	@ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable (name = "DBA.\"103_BOOKS_AUTHORS\"", joinColumns = @JoinColumn (name = "isbn"), 
				inverseJoinColumns = @JoinColumn (name = "author_id"))
	private Set<Author> authors = new HashSet<Author>();
	
	@JsonIgnore
	@ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable (name = "DBA.\"108_BOOKS_SUBJECTS\"", joinColumns = @JoinColumn (name = "isbn"), 
				inverseJoinColumns = @JoinColumn (name = "subject_id"))
	private Set<Subject> subjects = new HashSet<Subject>();
	
	@JsonBackReference
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
	private Set<BookCopy> copies = new HashSet<BookCopy>();
	
	public int getIsbn() {
		return isbn;
	}
	
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getNoOfPages() {
		return noOfPages;
	}
	
	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}
	
	public int getYearPublished() {
		return yearPublished;
	}
	
	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}
	
	public Publisher getPublisher() {
		return publisher;
	}
	
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	
	public Set<BookCopy> getCopies() {
		return copies;
	}

	public void setCopies(Set<BookCopy> copies) {
		this.copies = copies;
	}

	@Override
	public String toString() {
		return getTitle() + ", " + getYearPublished();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + isbn;
		result = prime * result + noOfPages;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + yearPublished;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn != other.isbn)
			return false;
		if (noOfPages != other.noOfPages)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (yearPublished != other.yearPublished)
			return false;
		return true;
	}
	
	
	
}
