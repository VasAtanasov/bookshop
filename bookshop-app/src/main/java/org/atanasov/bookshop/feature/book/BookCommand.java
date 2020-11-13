package org.atanasov.bookshop.feature.book;

import org.atanasov.bookshop.core.enums.AgeRestriction;
import org.atanasov.bookshop.core.enums.EditionType;
import org.atanasov.bookshop.feature.author.AuthorSubCommand;
import org.atanasov.bookshop.feature.common.Command;
import org.atanasov.bookshop.feature.common.OutputWriter;
import org.atanasov.bookshop.feature.error.BookshopException;
import org.atanasov.bookshop.feature.error.ErrorModel;
import org.atanasov.bookshop.models.ReducedBookServiceModel;
import org.atanasov.bookshop.services.BookService;
import org.atanasov.bookshop.utils.EnumUtils;
import org.atanasov.bookshop.utils.HelpUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class BookCommand implements Command {

  private final BookService bookService;
  private final OutputWriter writer;

  public BookCommand(BookService bookService, OutputWriter writer) {
    this.bookService = bookService;
    this.writer = writer;
  }

  @Override
  public void execute(List<String> arguments) {
    if (arguments.size() < 1) {
      throw new BookshopException(
          ErrorModel.builder()
              .message("Invalid number of arguments for \"book\" command.")
              .build());
    }

    BookSubCommand subCommand =
        EnumUtils.fromString(arguments.get(0), BookSubCommand.class)
            .orElseThrow(
                () ->
                    new BookshopException(
                        ErrorModel.builder()
                            .message("Invalid sub command: \"" + arguments.get(0) + "\"")
                            .build()));

    List<String> subCommandArgs = arguments.subList(1, arguments.size());
    if (subCommandArgs.size() < subCommand.getNArgs()) {
      throw new BookshopException(
          ErrorModel.builder()
              .message(
                  "Invalid number of arguments for \"" + subCommand.getCmd() + "\" sub command.")
              .build());
    }

    switch (subCommand) {
      case AR:
        printTitlesForAgeRestriction(subCommandArgs);
        break;
      case ET:
        printTitlesByEditionAndCopies(subCommandArgs);
        break;
      case PR:
        printBooksForPriceRange(subCommandArgs);
        break;
      case RY:
        printBooksNotReleasedInYear(subCommandArgs);
        break;
      case RD:
        printsBooksReleasedBefore(subCommandArgs);
        break;
      case TC:
        printsBooksTitleContains(subCommandArgs);
        break;
      case ANS:
        printBooksForAuthorNameStartingWith(subCommandArgs);
        break;
      case BTL:
        printsBooksTitleLength(subCommandArgs);
        break;
      case TTL:
        printReducedBook(subCommandArgs);
        break;
      case IC:
        modifyBooksCopies(subCommandArgs);
        break;
      case RM:
        removeBookWithLessThanCopies(subCommandArgs);
        break;
      default:
        throw new BookshopException(
            ErrorModel.builder().message("Unrecognized book sub command").build());
    }
  }

  @Override
  public String getName() {
    return "book";
  }

  @Override
  public String helpString() {
    Map<BookSubCommand, String> enumMap = EnumUtils.ENUM_MAP(BookSubCommand.class);
    return HelpUtil.getHelpString(enumMap.keySet(), getName());
  }

  private void removeBookWithLessThanCopies(List<String> subCommandArgs) {
    String numberString = subCommandArgs.get(0);
    try {
      int copies = Integer.parseInt(numberString);
      int count = bookService.deleteBooksWithCopiesLessThan(copies);
      writer.writeLine(String.format("Removed %d books from the database.", count));
    } catch (NumberFormatException nfe) {
      throw new BookshopException(
          ErrorModel.builder().message("Invalid number format: " + numberString).build());
    }
  }

  private void modifyBooksCopies(List<String> subCommandArgs) {
    String dateString = subCommandArgs.get(0);
    String numberString = subCommandArgs.get(1);
    try {
      LocalDate dateAfter = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
      int copies = Integer.parseInt(numberString);
      int modifiedCount = bookService.updateBookCopiesAfterDate(dateAfter, copies);
      String output =
          String.format(
              "%d books are released after %s, so total of %d book copies were added",
              modifiedCount, dateString, Math.abs(modifiedCount * copies));
      writer.writeLine(output);
    } catch (DateTimeParseException | NumberFormatException dte) {
      throw new BookshopException(
          ErrorModel.builder()
              .message("Invalid date format or invalid number: " + dateString + ", " + numberString)
              .build());
    }
  }

  private void printReducedBook(List<String> subCommandArgs) {
    String title = String.join(" ", subCommandArgs);
    Optional<ReducedBookServiceModel> book = bookService.findBookByTitle(title);
    if (book.isEmpty()) {
      writer.writeLine("Book with title \"" + title + "\" not found: ");
      return;
    }
    writer.writeLine(book);
  }

  private void printsBooksTitleLength(List<String> subCommandArgs) {
    try {
      int numberOfChars = Integer.parseInt(subCommandArgs.get(0));
      long numberOfBooks = bookService.booksCountForTitleLength(numberOfChars);
      writer.writeLine(
          String.format(
              "There are %d books with longer title than %d symbols.",
              numberOfBooks, numberOfChars));
    } catch (NumberFormatException nfe) {
      throw new BookshopException(ErrorModel.builder().message("Invalid number of chars").build());
    }
  }

  private void printBooksForAuthorNameStartingWith(List<String> subCommandArgs) {
    String searchString = subCommandArgs.get(0);
    bookService.findAllWithAuthorLastNameStarting(searchString).forEach(writer::writeLine);
  }

  private void printsBooksTitleContains(List<String> subCommandArgs) {
    String searchString = subCommandArgs.get(0);
    bookService.findAllWithTittleContaining(searchString).forEach(writer::writeLine);
  }

  private void printsBooksReleasedBefore(List<String> subCommandArgs) {
    String dateString = subCommandArgs.get(0);
    try {
      LocalDate dateBefore = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
      bookService.findAllWithReleaseDateBefore(dateBefore).stream()
          .map(
              book ->
                  String.format(
                      "%s %s %.2f", book.getTitle(), book.getEditionType(), book.getPrice()))
          .forEach(writer::writeLine);
    } catch (DateTimeParseException dte) {
      throw new BookshopException(
          ErrorModel.builder().message("Invalid date format: " + dateString).build());
    }
  }

  private void printBooksNotReleasedInYear(List<String> subCommandArgs) {
    String yearString = subCommandArgs.get(0);
    try {
      int year = Integer.parseInt(yearString);
      bookService.findAllNotReleased(year).forEach(writer::writeLine);
    } catch (NumberFormatException nfe) {
      throw new BookshopException(
          ErrorModel.builder().message("Invalid year: " + yearString).build());
    }
  }

  private void printBooksForPriceRange(List<String> subCommandArgs) {
    String lowerString = subCommandArgs.get(0);
    String highString = subCommandArgs.get(1);
    try {

      BigDecimal lowPrice = new BigDecimal(lowerString);
      BigDecimal highPrice = new BigDecimal(highString);

      if (lowPrice.compareTo(highPrice) >= 0) {
        throw new BookshopException(
            ErrorModel.builder()
                .message("Low price cannot be bigger or equal than high price")
                .build());
      }

      bookService.findAllWithPriceBetweenDesc(lowPrice, highPrice).forEach(writer::writeLine);

    } catch (NumberFormatException nfe) {
      throw new BookshopException(
          ErrorModel.builder()
              .message(
                  "Invalid price range:\n\t"
                      + "- low price range - "
                      + lowerString
                      + ";\n\t"
                      + "- high price range - "
                      + highString
                      + ";")
              .build());
    }
  }

  private void printTitlesForAgeRestriction(List<String> subCommandArgs) {
    String ageRestrictionString = subCommandArgs.get(0);
    if (!EnumUtils.has(ageRestrictionString, AgeRestriction.class)) {
      throw new BookshopException(
          ErrorModel.builder()
              .message("Invalid age restriction: \"" + ageRestrictionString + "\"")
              .build());
    }
    bookService.findAllWithAgeRestriction(ageRestrictionString).forEach(writer::writeLine);
  }

  private void printTitlesByEditionAndCopies(List<String> subCommandArgs) {
    String editionTypeString = subCommandArgs.get(0);

    if (!EnumUtils.has(editionTypeString, EditionType.class)) {
      throw new BookshopException(
          ErrorModel.builder()
              .message("Invalid edition type: \"" + editionTypeString + "\"")
              .build());
    }

    int copies;
    try {
      copies = Integer.parseInt(subCommandArgs.get(1));
    } catch (NumberFormatException nfe) {
      throw new BookshopException(
          ErrorModel.builder()
              .message("Invalid argument \"copies\" input: \"" + subCommandArgs.get(0) + "\"")
              .build());
    }
    bookService
        .findAllWithEditionAndCopiesLesThan(editionTypeString, copies)
        .forEach(writer::writeLine);
  }
}
