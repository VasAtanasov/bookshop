package org.atanasov.bookshop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ReducedBookServiceModel {
  private String title;
  private String editionType;
  private String ageRestriction;
  private BigDecimal price;
}
