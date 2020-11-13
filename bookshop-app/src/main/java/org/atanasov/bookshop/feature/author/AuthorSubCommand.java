package org.atanasov.bookshop.feature.author;

import lombok.Getter;
import org.atanasov.bookshop.feature.common.SubCommand;

@Getter
public enum AuthorSubCommand implements SubCommand {
  FN(
      "FN",
      "Prints the names of those authors, whose first name ends with a given string.",
      1,
      "ends_with",
      "string"),
  ATL(
      "ATL",
      "Prints the total number of book copies by author. Orders the results descending by total book copies.",
      0),
  TTL(
      "TTL",
      "Prints the total number of books for author's first and last name.",
      2,
      "first_name",
      "string",
      "last_name",
      "string");

  private final String cmd;
  private final String description;
  private final int nArgs;
  private final String[] argsNames;

  AuthorSubCommand(String cmd, String description, int nArgs, String... argsNames) {
    this.cmd = cmd;
    this.description = description;
    this.nArgs = nArgs;
    this.argsNames = argsNames;
  }
}
