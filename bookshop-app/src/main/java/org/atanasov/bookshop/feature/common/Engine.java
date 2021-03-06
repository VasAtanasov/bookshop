package org.atanasov.bookshop.feature.common;

import org.atanasov.bookshop.feature.error.BookshopException;
import org.atanasov.bookshop.feature.error.ErrorModel;
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
  private static final String HELP_COMMAND = "help";

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

      if (HELP_COMMAND.equals(input)) {
        printHelp();
        continue;
      }

      try {
        List<String> tokens = Arrays.asList(input.split("\\s+"));
        checkNumberOfArguments(tokens);
        Command command = getCommand(tokens.get(0));
        List<String> arguments = tokens.subList(1, tokens.size());
        command.execute(arguments);
      } catch (BookshopException be) {
        writer.writeLine(be.getMessage());
        printHelp();
      }
    }
  }

  private void checkNumberOfArguments(List<String> tokens) {
    if (tokens.size() == 0) {
      throw new BookshopException(
          ErrorModel.builder().message("Invalid number of arguments").build());
    }
  }

  private Command getCommand(String commandArg) {
    try {
      String commandName = COMMAND_NAME_SUPPLIER.apply(commandArg);
      return context.getBean(commandName, Command.class);
    } catch (BeansException ignored) {
      throw new BookshopException(
          ErrorModel.builder().message("Invalid main command: \"" + commandArg + "\"").build());
    }
  }

  private void printHelp() {
    System.out.println();
    System.out.println("Bookshop Management System");
    Arrays.stream(context.getBeanDefinitionNames())
        .filter(s -> s.endsWith("Command"))
        .map(s -> context.getBean(s, Command.class))
        .map(Command::helpString)
        .forEach(System.out::println);
    System.out.println("Main command: " + HELP_COMMAND + " - Prints commands description");
    System.out.println("Main command: " + END_COMMAND + " - Quits the application");
    System.out.println();
  }
}
