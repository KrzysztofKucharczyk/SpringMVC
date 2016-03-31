package pl.spring.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Controller
@ResponseBody
public class BookSearchService {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "rbooks/search", method = RequestMethod.GET)
	public ResponseEntity<List<BookTo>> searchBooks(@RequestParam("title") String title,
			@RequestParam("authors") String authors) {
		List<BookTo> results = new ArrayList<>();
		List<BookTo> titlesList = new ArrayList<>();
		List<BookTo> authorsList = new ArrayList<>();

		if (title.equals("") && authors.equals(""))
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);

		if (title.equals(""))
			return new ResponseEntity<List<BookTo>>(bookService.findBooksByAuthor(authors), HttpStatus.OK);

		if (authors.equals(""))
			return new ResponseEntity<List<BookTo>>(bookService.findBooksByTitle(title), HttpStatus.OK);

		titlesList = bookService.findBooksByTitle(title);
		authorsList = bookService.findBooksByAuthor(authors);

		for (BookTo book : authorsList)
			if (titlesList.contains(book))
				results.add(book);

		return new ResponseEntity<List<BookTo>>(results, HttpStatus.OK);
	}
}