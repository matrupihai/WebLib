package com.weblib.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.weblib.dao.AuthorDAOImpl;
import com.weblib.hbm.model.Author;

public class AuthorDAOImplTest {

	@Test
	public void testFindAllAuthors() {
		AuthorDAOImpl dao = new AuthorDAOImpl();
		List<Author> list = dao.findAllAuthors();
		assertEquals("At least one author should exist", true, !list.isEmpty());
	}

}
