package org.atanasov.bookshop.core.repository;

import org.atanasov.bookshop.core.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
  List<Book> findAllByReleaseDateAfter(LocalDate year);
}
