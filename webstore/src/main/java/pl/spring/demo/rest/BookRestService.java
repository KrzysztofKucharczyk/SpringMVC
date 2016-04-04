package pl.spring.demo.rest;

import java.util.ArrayList;
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

/*
 * Implementation of all CRUD methods.
 */

@Controller
@ResponseBody
public class BookRestService {

	/*
	 * Response class models the string used to get some details about request
	 * and respond.
	 */
	public class Response {
		private String response;

		public Response(String response) {
			this.response = response;
		}

		public String getResponse() {
			return response;
		}
	};

	@Autowired
	private BookService bookService;

	/*
	 * Method returns one book object with given id Returns HttpStatus.OK (200)
	 * with book-json in ResponseBody if book was obtained properly or
	 * HttpStatus.NOT_FOUND (404) if there is no book with given id in
	 * repository.
	 * 
	 * 
	 * Example: GET: http://localhost:8080/webstore/rbook/1
	 * 
	 * Response: { "id": 1, "title": "First book", "authors": "Jan Kowalski",
	 * "status": "FREE" }
	 * 
	 */
	@RequestMapping(value = "rbook/{id}", method = RequestMethod.GET)
	public ResponseEntity<BookTo> getBook(@PathVariable("id") long id) {
		BookTo book = bookService.findBookById(id);
		return (book != null) ? new ResponseEntity<BookTo>(book, HttpStatus.OK)
				: new ResponseEntity<BookTo>(HttpStatus.NOT_FOUND);
	}

	/*
	 * Method returns list of books objects. Returns always HttpStatus.OK (200)
	 * with books-json in ResponseBody if books were obtained properly, even if
	 * the list is empty.
	 * 
	 * 
	 * Example: GET: http://localhost:8080/webstore/rbooks/
	 * 
	 * Response: [ { "id": 1, "title": "First book", "authors": "Jan Kowalski",
	 * "status": "FREE" }, { "id": 2, "title": "Second book", "authors":
	 * "Zbigniew Nowak", "status": "FREE" }]
	 */
	@RequestMapping(value = "rbooks/", method = RequestMethod.GET)
	public ResponseEntity<List<BookTo>> getBooks() {
		List<BookTo> books = bookService.findAllBooks();

		return new ResponseEntity<List<BookTo>>(books, HttpStatus.OK);
	}

	/*
	 * Method creates new book entity in book repository. Require bookTo json in
	 * RequestBody. Return HttpStatus.OK (200) if book was saved with success in
	 * database, HttpStatus.CONFLICT (409) if the book already exist in the
	 * repository or HttpStatus.BAD_REQUEST if RequestBody does not match bookTo
	 * json.
	 * 
	 * Example: POST: http://localhost:8080/webstore/rbooks/ RequestBody: {
	 * "title": "First book", "authors": "Jan Kowalski", "status": "FREE" }
	 * 
	 * Response: { "response": "Book has been added to the database." }
	 */
	@RequestMapping(value = "rbooks/", method = RequestMethod.POST)
	public ResponseEntity<Response> createBook(@RequestBody BookTo bookTo) {
		BookTo newBook = bookTo;
		boolean alreadyInDB = false;

		List<BookTo> allBooks = bookService.findAllBooks();
		for (BookTo book : allBooks)
			if (book.getTitle().equals(bookTo.getTitle()) && book.getAuthors().equals(book.getAuthors()))
				alreadyInDB = true;

		if (alreadyInDB)
			return new ResponseEntity<Response>(new Response("The book is already in the database."),
					HttpStatus.CONFLICT);

		bookService.saveBook(newBook);

		return (newBook != null)
				? new ResponseEntity<Response>(new Response("Book has been added to the database."), HttpStatus.OK)
				: new ResponseEntity<Response>(new Response("Cannot find book to add. Check request parameters."),
						HttpStatus.BAD_REQUEST);
	}

	/*
	 * Method updates book in the repository with given parameters. Requires id
	 * of book choosen for update, json bookTo in RequestBody. Return
	 * HttpStatus.OK (200) in case of success, HttpStatus.NOT_FOUND (404) if
	 * there is no book with given id.
	 * 
	 * Example: PUT http://localhost:8080/webstore/rbook/1 RequestBody: { "id":
	 * 1, "title": "First ddd", "authors": "Jan Kowalski", "status": "FREE" }
	 * 
	 * 
	 * Response: { "response": "Book 'First ddd' has been updated." }
	 */
	@RequestMapping(value = "rbook/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateBook(@PathVariable("id") long id, @RequestBody BookTo bookTo) {

		if (bookTo != null) {
			bookService.updateBook(bookTo);
		} else
			return new ResponseEntity<Response>(new Response("Requested book has not been found in the database."),
					HttpStatus.NOT_FOUND);

		return new ResponseEntity<Response>(
				new Response(new String("Book \'" + bookTo.getTitle() + "\' has been updated.")), HttpStatus.OK);
	}

	/*
	 * Method removes book from bookRepository. Requires id of book chosen to
	 * removal. Return HttpStatus.OK (200) if book was deleted successfully or
	 * HttpStatus.NOT_FOUND if book with given id was not found in repository.
	 * 
	 * Example: DELETE http://localhost:8080/webstore/rbook/1
	 * 
	 * Response: Book has been purged from the database.
	 */
	@RequestMapping(value = "rbook/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> removeBook(@PathVariable("id") long id) {
		BookTo bookToRemove = bookService.findBookById(id);

		if (bookToRemove != null) {
			bookService.deleteBook(id);
			return new ResponseEntity<Response>(new Response("Book has been purged from the database."), HttpStatus.OK);
		} else
			return new ResponseEntity<Response>(new Response("Requested book has not been found in the database."),
					HttpStatus.NOT_FOUND);
	}

	/*
	 * Method searches for books. Requires title and author.
	 * 
	 * Example: GET http://localhost:8080/webstore/rbooks/First/Jan
	 * 
	 * Response: [ { "id": 1, "title": "First book", "authors": "Jan Kowalski",
	 * "status": "FREE" }]
	 */
	@RequestMapping(value = "rbooks/{title}/{authors}", method = RequestMethod.GET)
	public ResponseEntity<List<BookTo>> searchBooks(@PathVariable("title") String title,
			@PathVariable("authors") String authors) {
		List<BookTo> results = new ArrayList<>();
		List<BookTo> titlesList = new ArrayList<>();
		List<BookTo> authorsList = new ArrayList<>();

		if (title.equals("") && authors.equals(""))
			return new ResponseEntity<List<BookTo>>(results, HttpStatus.BAD_REQUEST);
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

		}
		return new ResponseEntity<List<BookTo>>(results, HttpStatus.OK);
	}

}
