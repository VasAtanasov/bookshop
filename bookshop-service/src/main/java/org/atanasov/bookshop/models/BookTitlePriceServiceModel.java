package org.atanasov.bookshop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class BookTitlePriceServiceModel {
  private String title;
  private BigDecimal price;

  @Override
  public String toString() {
    return String.format("%s - $%s", title, price);
  }
}
