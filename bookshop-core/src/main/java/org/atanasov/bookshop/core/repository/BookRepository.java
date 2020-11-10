package org.atanasov.bookshop.core.repository;

import org.atanasov.bookshop.core.domain.book.Book;
import org.atanasov.bookshop.core.dto.ReducedBookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
  List<Book> findAllByReleaseDateAfter(LocalDate year);

  List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(
      String firstName, String lastName);

  @Query(value = "SELECT * FROM books AS b WHERE YEAR(b.release_date) <> :year", nativeQuery = true)
  List<Book> findAllByReleaseDateNotLine(int year);

  @Query(
      "SELECT new org.atanasov.bookshop.core.dto.ReducedBookDTO (b.title, b.editionType, b.price, b.ageRestriction) "
          + "FROM Book b "
          + "WHERE b.title LIKE %:title%")
  ReducedBookDTO findByTitleLike(String title);

  @Transactional
  @Modifying
  @Query("UPDATE Book b SET b.copies = b.copies + :copies WHERE b.releaseDate > :after")
  int updateBookCopies(LocalDate after, int copies);

  @Transactional
  @Modifying
  int deleteBookByCopiesLessThan(int copies);
}
