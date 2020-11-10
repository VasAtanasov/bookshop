package org.atanasov.bookshop.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.bookshop.core.enums.AgeRestriction;
import org.atanasov.bookshop.core.enums.EditionType;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReducedBookDTO {
  private String title;
  private EditionType editionType;
  private BigDecimal price;
  private AgeRestriction ageRestriction;
}
