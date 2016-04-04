package pl.spring.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.enumerations.BookStatus;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("select book from BookEntity book where upper(book.title) like concat('%', upper(:title), '%')")
    public List<BookEntity> findBookByTitle(@Param("title") String title);

    @Query("select book from BookEntity book where upper(book.authors) like concat('%', upper(:author), '%')")
    public List<BookEntity> findBookByAuthor(@Param("author") String author);
    
    @Query("select book from BookEntity book where book.id = :id")
    public BookEntity findBookById(@Param("id") Long id);
    
    @Transactional
    @Modifying
    @Query("update BookEntity book set book.title = :title, book.authors = :authors, book.status = :status where book.id = :id ")
    public void updateBook(@Param("id") Long id, @Param("title") String title, @Param("authors") String authors, @Param("status") BookStatus status);
}
