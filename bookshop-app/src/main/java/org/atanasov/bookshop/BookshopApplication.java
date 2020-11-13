package org.atanasov.bookshop;

import org.atanasov.bookshop.feature.common.Engine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookshopApplication implements CommandLineRunner {

  private final Engine engine;

  public BookshopApplication(Engine engine) {
    this.engine = engine;
  }

  public static void main(String[] args) {
    SpringApplication.run(BookshopApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    engine.run();
    //    System.out.println(Constants.QUERYING_TASK_01);
    //    String restriction = reader.readLine().toUpperCase();
    //    this.bookService.findAllWithAgeRestriction(restriction).forEach(System.out::println);
    //    reader.close();

    //    bookService.findAllWithEditionAndCopiesLesThan("gold", 5000).forEach(System.out::println);

    //    bookService
    //        .findAllWithPriceBetweenDesc(BigDecimal.valueOf(5), BigDecimal.valueOf(50))
    //        .forEach(System.out::println);

    //    bookService.findAllNotReleased(2000).forEach(System.out::println);

    //    bookService
    //        .findAllWithReleaseDateBefore(
    //            LocalDate.parse("30-12-1989", DateTimeFormatter.ofPattern("dd-MM-yyyy")))
    //        .stream()
    //        .map(
    //            book ->
    //                String.format("%s %s %.2f", book.getTitle(), book.getEditionType(),
    // book.getPrice()))
    //        .forEach(System.out::println);

    //    authorService
    //        .findAllWithFirstNameEndingOn("dy")
    //        .forEach(
    //            a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName()));

    //    bookService.findAllWithTittleContaining("WOR").forEach(System.out::println);

    //    bookService.findAllWithAuthorLastNameStarting("gr").forEach(System.out::println);

    //    System.out.println(bookService.booksCountForTitleLength(12));

    //    authorService.authorTotalBookCopies().forEach(System.out::println);

    //    ReducedBookServiceModel book = bookService.findBookByTitle("Things Fall Apart");

    //    int count =
    //        bookService.updateBookCopiesAfterDate(
    //            LocalDate.parse("06 Jun 2013", DateTimeFormatter.ofPattern("dd MMM yyyy")), 44);

    //    long beforeDelete = bookRepository.count();
    //    authorService.getAuthorBooksCount("Amanda", "Rice").forEach(System.out::println);
    //    int count = bookService.deleteBooksWithCopiesLessThan(5000);
    //    long afterDelete = bookRepository.count();
    //    boolean leftJustifiedRows = true;
    //
    //    System.out.println(System.lineSeparator());
    //    System.out.println("Task 1: All books titles after the year 2000.");
    //    String[][] booksAfterYearHeaders = {{"Book Title"}};
    //    String[][] booksAfterYear =
    //        Stream.concat(
    //                Arrays.stream(booksAfterYearHeaders),
    //                bookRepository.findAllByReleaseDateAfter(LocalDate.of(2000, 12, 31)).stream()
    //                    .map(
    //                        book -> {
    //                          String[] values = new String[1];
    //                          values[0] = book.getTitle();
    //                          return values;
    //                        }))
    //            .toArray(String[][]::new);
    //
    //    TablePrintUtil.printTable(booksAfterYear, booksAfterYearHeaders, leftJustifiedRows);
    //
    //    System.out.println(System.lineSeparator());
    //    System.out.println(
    //        "Task 2: All authors first and last name with at least one book with release date
    // before 1990.");
    //
    //    String[][] authorsWithBooksAfterHeaders = {{"First Name", "Last Name"}};
    //    String[][] authorsWithBooksAfter =
    //        Stream.concat(
    //                Arrays.stream(authorsWithBooksAfterHeaders),
    //                authorRepository.findAllWithBooksBefore(LocalDate.of(1990, 1, 1)).stream()
    //                    .map(
    //                        author -> {
    //                          String[] values = new String[2];
    //                          values[0] = author.getFirstName();
    //                          values[1] = author.getLastName();
    //                          return values;
    //                        }))
    //            .toArray(String[][]::new);
    //
    //    TablePrintUtil.printTable(
    //        authorsWithBooksAfter, authorsWithBooksAfterHeaders, leftJustifiedRows);
    //
    //    System.out.println(System.lineSeparator());
    //    System.out.println(
    //        "Task 3: All authors first name, last name and books count, ordered by the number of
    // their books (descending).");
    //
    //    String[][] authorsWithBooksCountHeaders = {{"First Name", "Last Name", "Books Count"}};
    //    String[][] authorsWithBooksCount =
    //        Stream.concat(
    //                Arrays.stream(authorsWithBooksCountHeaders),
    //                authorRepository.getAllWithBooksCount().stream()
    //                    .map(
    //                        dto -> {
    //                          String[] values = new String[3];
    //                          values[0] = dto.getFirstName();
    //                          values[1] = dto.getLastName();
    //                          values[2] = String.valueOf(dto.getBooksCount());
    //                          return values;
    //                        }))
    //            .toArray(String[][]::new);
    //
    //    TablePrintUtil.printTable(
    //        authorsWithBooksCount, authorsWithBooksCountHeaders, leftJustifiedRows);
    //
    //    System.out.println(System.lineSeparator());
    //    System.out.println(
    //        "Task 3: All books title, release date and copies from author George Powell, ordered
    // by their release date (descending).");
    //
    //    String[][] booksForAuthorTableHeaders = {{"Title", "Release Date", "Copies"}};
    //    String[][] booksForAuthorTable =
    //        Stream.concat(
    //                Arrays.stream(booksForAuthorTableHeaders),
    //                bookRepository
    //                    .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(
    //                        "George", "Powell")
    //                    .stream()
    //                    .map(
    //                        book -> {
    //                          String[] values = new String[3];
    //                          values[0] = book.getTitle();
    //                          values[1] = book.getReleaseDate().toString();
    //                          values[2] = String.valueOf(book.getCopies());
    //                          return values;
    //                        }))
    //            .toArray(String[][]::new);
    //
    //    TablePrintUtil.printTable(booksForAuthorTable, booksForAuthorTableHeaders,
    // leftJustifiedRows);
  }
}
