package org.atanasov.bookshop.feature.book;

import org.atanasov.bookshop.feature.common.Command;
import org.atanasov.bookshop.services.BookService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookCommand implements Command {

  private final BookService bookService;

  public BookCommand(BookService bookService) {
    this.bookService = bookService;
  }

  @Override
  public String execute(List<String> arguments) {
    return null;
  }
}
