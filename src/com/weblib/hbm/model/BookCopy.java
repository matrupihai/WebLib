package com.weblib.hbm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table (name = "DBA.\"104_BOOKS_COPIES\"")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookCopy {
	@Id
	@Column (name = "copy_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	int copyId;
	
	@JsonManagedReference
	@ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn (name = "isbn")
	private Book book;
	
	@JsonIgnore
	@OneToOne (fetch = FetchType.LAZY, mappedBy = "bookCopy", cascade = CascadeType.ALL)
	private Loan loan;
	
	public BookCopy() {
		
	}
	
	public int getCopyId() {
		return copyId;
	}
	
	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + copyId;
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
		BookCopy other = (BookCopy) obj;
		if (copyId != other.copyId)
			return false;
		return true;
	}
	
}
