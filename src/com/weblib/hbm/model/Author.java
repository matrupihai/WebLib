package com.weblib.hbm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table (name="DBA.\"100_AUTHORS\"")
public class Author implements Serializable {

	@Id
	@Column (name="author_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int authorId;

	@Column (name="author_name")
	private String authorName;
	
	@JsonBackReference
	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "authors")
	private Set<Book> books = new HashSet<Book>();
	
	public Author(int authorId, String authorName, Set<Book> books) {
		 this.authorId = authorId;
		 this.authorName = authorName;
		 this.books = books;
	}
	
	public Author() {
		
	}
	
	public int getAuthorId() {
		return authorId;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	@Override
	public String toString() {
		return getAuthorName() + ": " + books;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + authorId;
		result = prime * result
				+ ((authorName == null) ? 0 : authorName.hashCode());
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
		Author other = (Author) obj;
		if (authorId != other.authorId)
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		return true;
	}

	
}
