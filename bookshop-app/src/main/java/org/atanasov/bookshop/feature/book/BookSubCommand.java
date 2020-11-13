package org.atanasov.bookshop.feature.book;

import lombok.Getter;
import org.atanasov.bookshop.feature.common.SubCommand;

@Getter
public enum BookSubCommand implements SubCommand {
  AR(
      "AR",
      "Prints the titles of all books, for which the age restriction matches the given input.",
      1,
      "age_restriction",
      "string"),
  ET(
      "ET",
      "Prints the titles of the given edition type, which have less than the given copies.",
      2,
      "edition_type",
      "string",
      "copes",
      "int"),
  PR(
      "PR",
      "Prints the titles and prices of books with given lower and higher price.",
      2,
      "lower_price",
      "double",
      "higher_price",
      "double"),
  RY(
      "RY",
      "Prints the titles of all books that are NOT released in a given year.",
      1,
      "year",
      "int"),
  RD(
      "RD",
      "Prints the title, the edition type and the price of books, which are released before a given date. The date will be in the format dd-MM-yyyy.",
      1,
      "date(dd-MM-yyyy)",
      "string"),
  TC(
      "TC",
      "Prints the titles of books, which contain a given string (regardless of the casing).",
      1,
      "title",
      "string"),
  ANS(
      "ANS",
      "Prints the titles of books, which are written by authors, whose last name starts with a given string.",
      1,
      "starts_with",
      "string"),
  BTL(
      "BTL",
      "Prints the number of books, whose title is longer than a given number.",
      1,
      "char_count",
      "int"),
  TTL(
      "TTL",
      "Prints information (title, edition type, age restriction and price) for a book by given title. ",
      1,
      "title",
      "string"),
  IC(
      "IC",
      "Increases the copies of all books released after a given date with a given number. Prints the total amount of book copies that were added.",
      2,
      "date(dd-MM-yyyy)",
      "string",
      "copies",
      "int"),
  RM(
      "IC",
      "Removes from the database those books, which copies are lower than a given number. Prints the number of books that were deleted from the database.",
      1,
      "copies",
      "int");

  private final String cmd;
  private final String description;
  private final int nArgs;
  private final String[] argsNames;

  BookSubCommand(String cmd, String description, int nArgs, String... argsNames) {
    this.cmd = cmd;
    this.description = description;
    this.nArgs = nArgs;
    this.argsNames = argsNames;
  }
}
