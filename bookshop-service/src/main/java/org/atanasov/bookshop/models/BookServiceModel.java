package org.atanasov.bookshop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.atanasov.bookshop.core.domain.author.Author;
import org.atanasov.bookshop.core.enums.AgeRestriction;
import org.atanasov.bookshop.core.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookServiceModel {
  private Long id;
  private Author author;
  private String title;
  private String description;
  private EditionType editionType;
  private BigDecimal price;
  private int copies;
  private LocalDate releaseDate;
  private AgeRestriction ageRestriction;
}
