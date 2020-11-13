package org.atanasov.bookshop.feature.author;

import liquibase.pro.packaged.S;
import org.atanasov.bookshop.feature.common.Command;
import org.atanasov.bookshop.feature.common.OutputWriter;
import org.atanasov.bookshop.feature.error.BookshopException;
import org.atanasov.bookshop.feature.error.ErrorModel;
import org.atanasov.bookshop.services.AuthorService;
import org.atanasov.bookshop.utils.EnumUtils;
import org.atanasov.bookshop.utils.HelpUtil;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthorCommand implements Command {
  private final AuthorService authorService;
  private final OutputWriter writer;

  public AuthorCommand(AuthorService authorService, OutputWriter writer) {
    this.authorService = authorService;
    this.writer = writer;
  }

  @Override
  public void execute(List<String> arguments) {
    if (arguments.size() < 1) {
      throw new BookshopException(
          ErrorModel.builder()
              .message("Invalid number of arguments for \"author\" command.")
              .build());
    }

    AuthorSubCommand subCommand =
        EnumUtils.fromString(arguments.get(0), AuthorSubCommand.class)
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
      case FN:
        printAuthorsFirstNameEndsWith(subCommandArgs);
        break;
      case ATL:
        printAuthorsBooksCount();
        break;
      case TTL:
        printTotalBooksForAuthor(subCommandArgs);
        break;
    }
  }

  @Override
  public String getName() {
    return "author";
  }

  @Override
  public String helpString() {
    Map<AuthorSubCommand, String> enumMap = EnumUtils.ENUM_MAP(AuthorSubCommand.class);
    return HelpUtil.getHelpString(enumMap.keySet(), getName());
  }

  private void printTotalBooksForAuthor(List<String> subCommandArgs) {
    String firstName = subCommandArgs.get(0);
    String lastName = subCommandArgs.get(1);
    try {
      authorService.getAuthorBooksCount(firstName, lastName).forEach(writer::writeLine);
    } catch (Exception ise) {
      throw new BookshopException(ErrorModel.builder().message("Currently unavailable").build());
    }
  }

  private void printAuthorsBooksCount() {
    authorService.authorTotalBookCopies().forEach(writer::writeLine);
  }

  private void printAuthorsFirstNameEndsWith(List<String> subCommandArgs) {
    String searchString = subCommandArgs.get(0);
    authorService
        .findAllWithFirstNameEndingOn(searchString)
        .forEach(a -> writer.writeLine(a.getFirstName() + " " + a.getLastName()));
  }
}
