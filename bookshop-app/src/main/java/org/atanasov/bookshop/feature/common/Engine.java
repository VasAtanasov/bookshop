package org.atanasov.bookshop.feature.common;

import org.atanasov.bookshop.feature.author.AuthorCommand;
import org.atanasov.bookshop.feature.book.BookCommand;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Engine {
  private static final String END_COMMAND = "exit";

  private final BookCommand bookCommand;
  private final AuthorCommand authorCommand;
  private final InputReader reader;
  private final OutputWriter writer;
  private final ApplicationContext context;

  public Engine(
      BookCommand bookCommand,
      AuthorCommand authorCommand,
      InputReader inputReader,
      OutputWriter outputWriter,
      ApplicationContext context) {
    this.bookCommand = bookCommand;
    this.authorCommand = authorCommand;
    this.reader = inputReader;
    this.writer = outputWriter;
    this.context = context;
  }

  public void run() {
    String input;
    do {
      input = reader.readLine();
      List<String> tokens = Arrays.asList(input.split("\\s+"));

      List<String> arguments = tokens.subList(1, tokens.size());

      Command command = context.getBean("AuthorCommand", Command.class);
      int a = 5;
      //      this.writer.writeLine(command.execute(arguments));

    } while (!END_COMMAND.equals(input));
  }
}
