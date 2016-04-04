package pl.spring.demo.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.spring.demo.enumerations.BookStatus;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.web.utils.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class BookRestServiceTest {

	@Autowired
	private BookService bookService;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		Mockito.reset(bookService);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testShouldFindBookById() throws Exception {

		// given:
		final Long id = 2L;
		final BookTo bookTo1 = new BookTo(id, "title", "Author1", BookStatus.FREE);

		// register response for bookService.findBookById(int id) mock
		Mockito.when(bookService.findBookById(id)).thenReturn(bookTo1);
		// when
		mockMvc.perform(get("/rbook/" + id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content("1")).andExpect(status().isOk()).andExpect(jsonPath("id").value(bookTo1.getId().intValue()))
				.andExpect(jsonPath("title").value(bookTo1.getTitle()))
				.andExpect(jsonPath("authors").value(bookTo1.getAuthors()));
	}

	@Test
	public void testShouldGetAllBooks() throws Exception {

		// given:
		final BookTo bookTo1 = new BookTo(1L, "title", "Author1", BookStatus.FREE);
		final BookTo bookTo2 = new BookTo(1L, "other title", "Author2", BookStatus.MISSING);

		// register response for bookService.findAllBooks() mock
		Mockito.when(bookService.findAllBooks()).thenReturn(Arrays.asList(bookTo1, bookTo2));
		// when
		ResultActions response = this.mockMvc.perform(get("/rbooks/").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("1"));

		response.andExpect(status().isOk()).andExpect(jsonPath("[0].id").value(bookTo1.getId().intValue()))
				.andExpect(jsonPath("[0].title").value(bookTo1.getTitle()))
				.andExpect(jsonPath("[0].authors").value(bookTo1.getAuthors()));

		response.andExpect(status().isOk()).andExpect(jsonPath("[1].id").value(bookTo2.getId().intValue()))
				.andExpect(jsonPath("[1].title").value(bookTo2.getTitle()))
				.andExpect(jsonPath("[1].authors").value(bookTo2.getAuthors()));
	}

	@Test
	public void testShouldSaveBookAndSucceed() throws Exception {
		// given
		File file = FileUtils.getFileFromClasspath("classpath:pl/spring/demo/web/json/goodBook.json");
		String json = FileUtils.readFileToString(file);

		// when
		ResultActions response = mockMvc.perform(post("/rbooks/").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json.getBytes()));
		// then
		response.andExpect(status().isOk())
				.andExpect(jsonPath("response").value("Book has been added to the database."));
	}

	@Test
	public void testShouldSaveBookAndFail() throws Exception {
		// given
		BookTo book = new BookTo(1L, "First book", "Jan Kowalski", BookStatus.FREE);

		File file = FileUtils.getFileFromClasspath("classpath:pl/spring/demo/web/json/badBook.json");
		String json = FileUtils.readFileToString(file);

		// Mockito.when(bookService.saveBook(book)).thenReturn(book);

		// when
		ResultActions response = mockMvc.perform(post("/rbooks/").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json.getBytes()));
		// then
		response.andExpect(status().isConflict())
				.andExpect(jsonPath("response").value("Cannot find book to add. Check request parameters."));
	}

	@Test
	public void testShouldUpdateBook() throws Exception {
		// given
		final Long id = 1L;
		File file = FileUtils.getFileFromClasspath("classpath:pl/spring/demo/web/json/goodBook.json");
		String json = FileUtils.readFileToString(file);

		// when
		ResultActions response = this.mockMvc.perform(put("/rbook/" + id).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json.getBytes()));

		// then
		response.andExpect(status().isOk());
	}

	@Test
	public void testShouldRemoveBookById() throws Exception {

		// given
		final Long id = 1L;

		BookTo bookTo = new BookTo(id, "Title", "Authors", BookStatus.FREE);
		bookService.saveBook(bookTo);

		// when
		ResultActions response = this.mockMvc.perform(delete("/rbook/" + id).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("1"));

		response.andExpect(status().isOk());
	}

}
