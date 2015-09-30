package com.weblib.dao;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.weblib.hbm.model.Author;

@RunWith(JUnitParamsRunner.class)
public class AuthorDAOImplTest {
	
	private AuthorDAOImpl dao = new AuthorDAOImpl();
	
	@Test
	@Parameters (method = "paramsForFindAuthorByName")
	public void find_author_by_name(String name) {
		Author author = dao.findAuthorByName(name);
		
		assertThat(author.getAuthorName(), is(name));
	}
	
	public static Object[] paramsForFindAuthorByName() {
		return $(
					$("albert camus"),
					$("george orwell")
				);
	}
	
	@After
	public void sayGoodBye() {
		System.out.println("Good bye " + this.getClass().getSimpleName());
	}
	
}
