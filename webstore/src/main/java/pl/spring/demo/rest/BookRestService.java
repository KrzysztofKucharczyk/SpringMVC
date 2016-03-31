package pl.spring.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Controller
@ResponseBody
public class BookRestService {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "rbook/{id}", method = RequestMethod.GET)
	public ResponseEntity<BookTo> getBook(@PathVariable("id") long id) {
		BookTo book = bookService.findBookById(id);
		return (book != null) ? new ResponseEntity<BookTo>(book, HttpStatus.OK)
				: new ResponseEntity<BookTo>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "rbooks/", method = RequestMethod.GET)
	public ResponseEntity<List<BookTo>> getBooks() {
		List<BookTo> books = bookService.findAllBooks();

		return (books != null) ? new ResponseEntity<List<BookTo>>(books, HttpStatus.OK)
				: new ResponseEntity<List<BookTo>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "rbooks/", method = RequestMethod.POST)
	public ResponseEntity<String> createBook(@RequestBody BookTo bookTo) {
		BookTo newBook = bookTo;

		if (bookService.findAllBooks().contains(newBook))
			return new ResponseEntity<String>("The book is already in the database.", HttpStatus.CONFLICT);

		bookService.saveBook(newBook);

		return (newBook != null) ? new ResponseEntity<String>("Book has been added to the database.", HttpStatus.OK)
				: new ResponseEntity<String>("Cannot find book to add. Check request parameters.",
						HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "rbook/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBook(@PathVariable("id") long id, @RequestBody BookTo bookTo) {

		if (bookTo != null) {
			bookService.updateBook(bookTo);
		} else
			return new ResponseEntity<String>("Requested book has not been found in the database.",
					HttpStatus.NOT_FOUND);

		return new ResponseEntity<String>(
				new String(
						"Book " + bookTo.getTitle() + " has been updated with those data: " + bookTo.toString() + "."),
				HttpStatus.OK);
	}

	@RequestMapping(value = "rbook/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeBook(@PathVariable("id") long id) {
		BookTo bookToRemove = bookService.findBookById(id);

		if (bookToRemove != null) {
			bookService.deleteBook(id);
			return new ResponseEntity<String>("Book has been purged from the database.", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Requested book has not been found in the database.",
					HttpStatus.NOT_FOUND);
	}

}
