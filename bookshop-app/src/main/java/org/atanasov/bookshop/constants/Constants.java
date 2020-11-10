package org.atanasov.bookshop.constants;

public class Constants {

  //    EXERCISE: SPRING DATA INTRO

  public static final String INTRO_TASK_01 =
      "1.\tGets all books after the year 2000. Prints only their titles.";
  public static final String INTRO_TASK_02 =
      "2.\tGets all authors with at least one book with release date before 1990. Prints their first name and last name.";
  public static final String INTRO_TASK_03 =
      "3.\tGets all authors, ordered by the number of their books (descending). Prints their first name, last name and book count.";
  public static final String INTRO_TASK_04 =
      "4.\tGets all books from author George Powell, ordered by their release date (descending), then by book title (ascending).\nPrints the book's title, release date and copies.";

  //    EXERCISE: SPRING DATA ADVANCED QUERYING

  public static final String QUERYING_TASK_01 =
      "Prints the titles of all books, for which the age restriction matches the given input (minor, teen or adult).";
  public static final String QUERYING_TASK_02 =
      "Prints the titles of the golden edition books, which have less than 5000 copies.";
  public static final String QUERYING_TASK_03 =
      "Prints the titles and prices of books with price lower than 5 and higher than 40.";
  public static final String QUERYING_TASK_04 =
      "Prints the titles of all books that are NOT released in a given year.";
  public static final String QUERYING_TASK_05 =
      "Prints the title, the edition type and the price of books, which are released before a given date.\nThe date must be in the format dd-MM-yyyy.";
  public static final String QUERYING_TASK_06 =
      "Prints the names of those authors, whose first name ends with a given string.";
  public static final String QUERYING_TASK_07 =
      "Prints the titles of books, which contain a given string (regardless of the casing).";
  public static final String QUERYING_TASK_08 =
      "Prints the titles of books, which are written by authors, whose last name starts with a given string.";
  public static final String QUERYING_TASK_09 =
      "Prints the number of books, whose title is longer than a given number.";
  public static final String QUERYING_TASK_10 =
      "Prints the total number of book copies by author. Ordered descending by total book copies.";
  public static final String QUERYING_TASK_11 =
      "Prints information (title, edition type, age restriction and price) for a book by given title.";
  public static final String QUERYING_TASK_12 =
      "Increases the copies of all books released after a given date with a given number.\nPrints the total amount of book copies that were added.";
  public static final String QUERYING_TASK_13 =
      "Removes from the database those books, which copies are lower than a given number.\nPrint the number of books that were deleted from the database.";
}
