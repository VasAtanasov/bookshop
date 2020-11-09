package org.atanasov.bookshop.core.dto;

import lombok.Builder;
import lombok.Getter;
import org.atanasov.bookshop.core.domain.author.Author;
import org.atanasov.bookshop.core.domain.book.Book;
import org.atanasov.bookshop.core.enums.AgeRestriction;
import org.atanasov.bookshop.core.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class BookDTO {
  private Long id;
  private Author author;
  private String title;
  private String description;
  private EditionType editionType;
  private BigDecimal price;
  private int copies;
  private LocalDate releaseDate;
  private AgeRestriction ageRestriction;

  public static BookDTO withTitle(Book book) {
    return BookDTO.builder().releaseDate(book.getReleaseDate()).title(book.getTitle()).build();
  }
}
