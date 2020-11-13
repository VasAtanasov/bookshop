package org.atanasov.bookshop.feature.book;

import org.atanasov.bookshop.core.enums.AgeRestriction;
import org.atanasov.bookshop.feature.common.Command;
import org.atanasov.bookshop.feature.error.BookshopException;
import org.atanasov.bookshop.services.BookService;
import org.atanasov.bookshop.utils.EnumUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookCommand implements Command {

  private final BookService bookService;

  public BookCommand(BookService bookService) {
    this.bookService = bookService;
  }

  @Override
  public String execute(List<String> arguments) {
    if (arguments.size() < 1) {
      throw new BookshopException();
    }

    BookSubCommand subCommand =
        EnumUtils.fromString(arguments.get(0), BookSubCommand.class).orElse(null);

    if (subCommand == null) {
      throw new BookshopException();
    }

    List<String> subCommandArgs = arguments.subList(1, arguments.size());
    if (subCommandArgs.size() != subCommand.getNArgs()) {
      throw new BookshopException();
    }

    switch (subCommand) {
      case AR:
        this.bookService
            .findAllWithAgeRestriction(subCommandArgs.get(0))
            .forEach(System.out::println);
        break;
      case ET:
        break;
      case PR:
        break;
      case RY:
        break;
      case RD:
        break;
      case TC:
        break;
      case ANS:
        break;
      case BTL:
        break;
      case TTL:
        break;
      case IC:
        break;
      case RM:
        break;
      default:
    }

    return null;
  }
}
