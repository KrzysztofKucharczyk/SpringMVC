package pl.spring.demo.web.controller;

import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pl.spring.demo.controller.BookController;

public class BookControllerTest {

	@SuppressWarnings("unused")
	private MockMvc mockMvc;

	@Before
	public void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/templates/");
		viewResolver.setSuffix(".html");

		mockMvc = MockMvcBuilders.standaloneSetup(new BookController()).setViewResolvers(viewResolver).build();
	}

}
