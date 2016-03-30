package pl.spring.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

/**
 * Book controller
 * 
 * @author mmotowid
 *
 */
@Controller
@RequestMapping("/books")
public class BookController {

	private static final String PAGE_DESCRIPTION = "Here is a place to review all library's content. Feel free to touch book's covers, read about them and breath their ePub smell.";
	private static final String PAGE_NAME = "List of books";

	@Autowired
	private BookService bookService;

	@RequestMapping
	public String list(Model model) {
		model.addAttribute(ModelConstants.GREETING, PAGE_NAME);
		model.addAttribute(ModelConstants.INFO, PAGE_DESCRIPTION);
		model.addAttribute(ModelConstants.BOOK_LIST, bookService.findAllBooks());
		return ViewNames.BOOKS;
	}

	@RequestMapping(value = "/book")
	public String getBookById(@RequestParam("id") Long entryId, Model model) {
		BookTo book = bookService.findBookById(entryId);
		model.addAttribute("book", book);
		return ViewNames.BOOK;
	}

	@RequestMapping("/search")
	public String getBookById(Model model) {

		return ViewNames.SEARCH;
	}

	@RequestMapping("/searchDetails")
	public String getBookById(@RequestParam("title") String title, @RequestParam("authors") String authors,
			Model model) {
		List<BookTo> results = new ArrayList<>();
		List<BookTo> titlesList = new ArrayList<>();
		List<BookTo> authorsList = new ArrayList<>();

		if (title.equals("") && authors.equals(""))
			return ViewNames.SEARCH;
		else {

			if (title.equals(""))
				results = bookService.findBooksByAuthor(authors);

			else if (authors.equals(""))
				results = bookService.findBooksByTitle(title);
			
			else {
				titlesList = bookService.findBooksByTitle(title);
				authorsList = bookService.findBooksByAuthor(authors);
				
				for(BookTo book : authorsList)
					if(titlesList.contains(book))
						results.add(book);
			}
			
			model.addAttribute("foundBooks", results);
		}
		return ViewNames.SEARCH;
	}

	// TODO: here implement methods which displays book info based on query
	// arguments

	// TODO: Implement GET / POST methods for "add book" functionality

	/**
	 * Binder initialization
	 */
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "title", "authors", "status");
	}

}
