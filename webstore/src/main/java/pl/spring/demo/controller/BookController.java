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
import pl.spring.demo.enumerations.BookStatus;
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
	public String loadBookList(Model model) {
		model.addAttribute(ModelConstants.GREETING, PAGE_NAME);
		model.addAttribute(ModelConstants.INFO, PAGE_DESCRIPTION);
		model.addAttribute(ModelConstants.BOOK_LIST, bookService.findAllBooks());
		return ViewNames.BOOKS;
	}

	@RequestMapping(value = "/book")
	public String getBook(@RequestParam("id") Long entryId, Model model) {
		BookTo book = bookService.findBookById(entryId);
		model.addAttribute("book", book);
		return ViewNames.BOOK;
	}

	@RequestMapping("/search")
	public String getBooks(Model model) {
		return ViewNames.SEARCH;
	}

	@RequestMapping("/searchDetails")
	public String searchBook(@RequestParam("title") String title, @RequestParam("authors") String authors,
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

				for (BookTo book : authorsList)
					if (titlesList.contains(book))
						results.add(book);
			}

			model.addAttribute("foundBooks", results);
		}
		return ViewNames.SEARCH;
	}

	@RequestMapping(value = "/remove")
	public String prepareToRemoveBookById(@RequestParam("id") Long entryId, Model model) {
		BookTo book = bookService.findBookById(entryId);
		model.addAttribute("bookToRemove", book);
		return "acceptRemove";
	}

	@RequestMapping(value = "remove/removed")
	public String removeBook(@RequestParam("id") Long entryId, Model model) {
		bookService.deleteBook(entryId);
		return "removed";
	}

	@RequestMapping(value = "/add")
	public String loadAddView(Model model) {
		return "add";
	}

	@RequestMapping(value = "/adding")
	public String addBook(@RequestParam("title") String title, @RequestParam("authors") String authors,
			@RequestParam("status") String status) {

		BookTo newBook = new BookTo();

		newBook.setTitle(title);
		newBook.setAuthors(authors);

		if (status.equals("free"))
			newBook.setStatus(BookStatus.FREE);
		if (status.equals("loan"))
			newBook.setStatus(BookStatus.LOAN);
		if (status.equals("missing"))
			newBook.setStatus(BookStatus.MISSING);

		bookService.saveBook(newBook);

		return "added";
	}

	/**
	 * Binder initialization
	 */
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "title", "authors", "status");
	}

}
