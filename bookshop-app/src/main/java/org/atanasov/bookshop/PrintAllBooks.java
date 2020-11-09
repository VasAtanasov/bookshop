package org.atanasov.bookshop;

import org.atanasov.bookshop.core.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class PrintAllBooks {
  private static final Logger logger = LoggerFactory.getLogger(BookshopApplication.class);

  private final BookRepository bookRepository;

  public PrintAllBooks(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @ShellMethod("Translate text from one language to another.")
  public void printAuthor() {
    bookRepository
        .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc("George", "Powell")
        .forEach(
            book ->
                logger.info(
                    "Title: "
                        + book.getTitle()
                        + " Release Date: "
                        + book.getReleaseDate()
                        + " Copies: "
                        + book.getCopies()));
  }
}
