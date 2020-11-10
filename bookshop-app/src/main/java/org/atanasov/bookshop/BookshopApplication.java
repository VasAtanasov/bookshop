package org.atanasov.bookshop;

import org.atanasov.bookshop.core.repository.AuthorRepository;
import org.atanasov.bookshop.core.repository.BookRepository;
import org.atanasov.bookshop.utils.TablePrintUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootApplication
public class BookshopApplication implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;

  public BookshopApplication(AuthorRepository authorRepository, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(BookshopApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    boolean leftJustifiedRows = true;

    System.out.println(System.lineSeparator());
    System.out.println("Task 1: All books titles after the year 2000.");
    String[][] booksAfterYearHeaders = {{"Book Title"}};
    String[][] booksAfterYear =
        Stream.concat(
                Arrays.stream(booksAfterYearHeaders),
                bookRepository.findAllByReleaseDateAfter(LocalDate.of(2000, 12, 31)).stream()
                    .map(
                        book -> {
                          String[] values = new String[1];
                          values[0] = book.getTitle();
                          return values;
                        }))
            .toArray(String[][]::new);

    TablePrintUtil.printTable(booksAfterYear, booksAfterYearHeaders, leftJustifiedRows);

    System.out.println(System.lineSeparator());
    System.out.println(
        "Task 2: All authors first and last name with at least one book with release date before 1990.");

    String[][] authorsWithBooksAfterHeaders = {{"First Name", "Last Name"}};
    String[][] authorsWithBooksAfter =
        Stream.concat(
                Arrays.stream(authorsWithBooksAfterHeaders),
                authorRepository.findAllWithBooksBefore(LocalDate.of(1990, 1, 1)).stream()
                    .map(
                        author -> {
                          String[] values = new String[2];
                          values[0] = author.getFirstName();
                          values[1] = author.getLastName();
                          return values;
                        }))
            .toArray(String[][]::new);

    TablePrintUtil.printTable(
        authorsWithBooksAfter, authorsWithBooksAfterHeaders, leftJustifiedRows);

    System.out.println(System.lineSeparator());
    System.out.println(
        "Task 3: All authors first name, last name and books count, ordered by the number of their books (descending).");

    String[][] authorsWithBooksCountHeaders = {{"First Name", "Last Name", "Books Count"}};
    String[][] authorsWithBooksCount =
        Stream.concat(
                Arrays.stream(authorsWithBooksCountHeaders),
                authorRepository.getAllWithBooksCount().stream()
                    .map(
                        dto -> {
                          String[] values = new String[3];
                          values[0] = dto.getFirstName();
                          values[1] = dto.getLastName();
                          values[2] = String.valueOf(dto.getBooksCount());
                          return values;
                        }))
            .toArray(String[][]::new);

    TablePrintUtil.printTable(
        authorsWithBooksCount, authorsWithBooksCountHeaders, leftJustifiedRows);

    System.out.println(System.lineSeparator());
    System.out.println(
        "Task 3: All books title, release date and copies from author George Powell, ordered by their release date (descending).");

    String[][] booksForAuthorTableHeaders = {{"Title", "Release Date", "Copies"}};
    String[][] booksForAuthorTable =
        Stream.concat(
                Arrays.stream(booksForAuthorTableHeaders),
                bookRepository
                    .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(
                        "George", "Powell")
                    .stream()
                    .map(
                        book -> {
                          String[] values = new String[3];
                          values[0] = book.getTitle();
                          values[1] = book.getReleaseDate().toString();
                          values[2] = String.valueOf(book.getCopies());
                          return values;
                        }))
            .toArray(String[][]::new);

    TablePrintUtil.printTable(booksForAuthorTable, booksForAuthorTableHeaders, leftJustifiedRows);
  }
}
