package org.atanasov.bookshop.core.repository;

import org.atanasov.bookshop.core.domain.author.Author;
import org.atanasov.bookshop.core.dto.AuthorBooksCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository
    extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

  @Query(
      "SELECT DISTINCT(a) "
          + "FROM Author a "
          + "INNER JOIN a.books b "
          + "WHERE b.releaseDate < :before")
  List<Author> findAllWithBooksBefore(LocalDate before);

  @Query(
      "SELECT a.firstName AS firstName, a.lastName AS lastName, SIZE(a.books) AS booksCount "
          + "FROM Author a JOIN a.books b "
          + "GROUP BY a "
          + "ORDER BY SIZE(a.books) DESC ")
  List<AuthorBooksCountDTO> getAllWithBooksCount();


}
