package org.atanasov.bookshop;

import org.atanasov.bookshop.core.dto.BookTitleDTO;
import org.atanasov.bookshop.core.repository.AuthorRepository;
import org.atanasov.bookshop.core.repository.BookRepository;
import org.atanasov.bookshop.core.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class BookshopApplication implements CommandLineRunner {
  private static final Logger logger = LoggerFactory.getLogger(BookshopApplication.class);

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final CategoryRepository categoryRepository;

  public BookshopApplication(
      AuthorRepository authorRepository,
      BookRepository bookRepository,
      CategoryRepository categoryRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.categoryRepository = categoryRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(BookshopApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    bookRepository.findAllByReleaseDateAfter(LocalDate.of(2000, 12, 31)).stream()
        .map(BookTitleDTO::of)
        .forEach(bookDTO -> logger.info(bookDTO.toString()));
  }
}
