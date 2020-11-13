package org.atanasov.bookshop.feature.common;

import org.atanasov.bookshop.feature.error.BookshopException;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Component
public class Engine {
  private static final String END_COMMAND = "exit";
  private static final Function<String, String> COMMAND_NAME_SUPPLIER =
      (name) -> name.toLowerCase() + "Command";

  private final InputReader reader;
  private final OutputWriter writer;
  private final ApplicationContext context;

  public Engine(InputReader inputReader, OutputWriter outputWriter, ApplicationContext context) {
    this.reader = inputReader;
    this.writer = outputWriter;
    this.context = context;
  }

  public void run() {
    String input;
    while (true) {
      input = reader.readLine();
      if (END_COMMAND.equals(input)) {
        SpringApplication.exit(context, () -> 0);
        break;
      }

      List<String> tokens = Arrays.asList(input.split("\\s+"));

      if (tokens.size() == 0) {
        System.out.println("Invalid number of arguments");
        continue;
      }

      try {
        String commandName = COMMAND_NAME_SUPPLIER.apply(tokens.get(0));
        Command command = context.getBean(commandName, Command.class);
        List<String> arguments = tokens.subList(1, tokens.size());
        String result = command.execute(arguments);
        writer.writeLine(result);
      } catch (BeansException | BookshopException be) {
        // Print help
        System.out.println("Invalid command");
      }
    }
  }
}
