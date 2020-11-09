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
      value =
          "SELECT DISTINCT(a) "
              + "FROM Author a "
              + "INNER JOIN a.books b "
              + "WHERE b.releaseDate < :before")
  List<Author> findAllWithBooksBefore(LocalDate before);

  @Query(
      value =
          "SELECT a.firstName as firstName, a.lastName as lastName, size(a.books) as booksCount "
              + "from Author a join a.books b "
              + "group by a "
              + "order by size(a.books) desc ")
  List<AuthorBooksCountDTO> getAllWithBooksCount();
}
