package pl.spring.demo.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.enumerations.BookStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testShouldFindBookById() {
        // given
        final long bookId = 1;
        // when
        BookEntity bookEntity = bookRepository.findOne(bookId);
        // then
        assertNotNull(bookEntity);
        assertEquals("First book", bookEntity.getTitle());
    }
    
    @Test
    public void testShouldNotFindBookById() {
        // given
        final long bookId = 100;
        // when
        BookEntity bookEntity = bookRepository.findOne(bookId);
        // then
        assertNull(bookEntity);
    }

    @Test
    public void testShouldFindBooksByTitle() {
        // given
        final String bookTitle = "f";
        // when
        List<BookEntity> booksEntity = bookRepository.findBookByTitle(bookTitle);
        // then
        assertNotNull(booksEntity);
        assertFalse(booksEntity.isEmpty());
        assertEquals("First book", booksEntity.get(0).getTitle());
    }
    
    @Test
    public void testShouldNotFindBooksByTitle() {
        // given
        final String bookTitle = "x";
        // when
        List<BookEntity> booksEntity = bookRepository.findBookByTitle(bookTitle);
        // then
        assertNotNull(booksEntity);
        assertTrue(booksEntity.isEmpty());
    }
    
    @Test
    public void testShouldFindBooksByAuthors() {
        // given
        final String bookAuthors = "Jan";
        // when
        List<BookEntity> booksEntity = bookRepository.findBookByAuthor(bookAuthors);
        // then
        assertNotNull(booksEntity);
        assertFalse(booksEntity.isEmpty());
        assertEquals("Jan Kowalski", booksEntity.get(0).getAuthors());
    }
    
    @Test
    public void testShouldNotFindBooksByAuthors() {
        // given
        final String bookAuthors = "Xxx";
        // when
        List<BookEntity> booksEntity = bookRepository.findBookByAuthor(bookAuthors);
        // then
        assertNotNull(booksEntity);
        assertTrue(booksEntity.isEmpty());
    }
    
    @Test
    public void testShouldUpdateBookById() {
        // given
    	final Long id = 1L;
    	final String title = "Bobry";
        final String authors = "Krzysztof Wilk";
        final BookStatus status = BookStatus.FREE;
        // when
        bookRepository.updateBook(id, title, authors, status);
        // then
        BookEntity bookEntity = bookRepository.findBookById(id); 
        assertNotNull(bookEntity);
        assertEquals("Krzysztof Wilk", bookEntity.getAuthors());
    }
}
