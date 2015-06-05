package com.weblib.dao;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.weblib.hbm.model.Author;

@RunWith(JUnitParamsRunner.class)
public class AuthorDAOImplTest {
	
	private AuthorDAOImpl dao = new AuthorDAOImpl();
	
	@Before
	public void initDbConnection() {
		
	}
	
	@Test
	@Parameters (method = "paramsForFindAuthorByName")
	public void find_author_by_name(String name) {
		Author author = dao.findAuthorByName(name);
		assertThat(author.getAuthorName(), is(name));
	}
	
	public static Object[] paramsForFindAuthorByName() {
		return $(
				$("alber camus")
				);
	}
	
}
