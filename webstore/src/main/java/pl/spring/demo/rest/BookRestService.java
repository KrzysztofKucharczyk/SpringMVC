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
	public ResponseEntity<BookTo> createBook(@RequestBody BookTo bookTo) {
		BookTo newBook = bookTo;
		
		if (bookService.findAllBooks().contains(newBook))
			return new ResponseEntity<BookTo>(HttpStatus.CONFLICT);
		
		bookService.saveBook(newBook);
		
		return (newBook != null) ? new ResponseEntity<BookTo>(newBook, HttpStatus.OK)
				: new ResponseEntity<BookTo>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "rbook/{id}", method = RequestMethod.PUT)
	public ResponseEntity<BookTo> updateBook(@PathVariable("id") long id, @RequestBody BookTo bookTo) {
		
		if(bookTo != null) {
			bookService.updateBook(bookTo);			
		}
		else
			return new ResponseEntity<BookTo>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<BookTo>(bookTo, HttpStatus.OK);
	}

	@RequestMapping(value = "rbook/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<BookTo> removeBook(@PathVariable("id") long id) {
		BookTo bookToRemove = bookService.findBookById(id);
		
		if (bookToRemove != null) {
			bookService.deleteBook(id);
			return new ResponseEntity<BookTo>(bookToRemove, HttpStatus.OK);
		} else
			return new ResponseEntity<BookTo>(HttpStatus.NOT_FOUND);
	}

	// TODO: implement some search methods considering single request parameters
	// / multiple request parameters / array request parameters

}
