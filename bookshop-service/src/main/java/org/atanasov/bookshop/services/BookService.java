package org.atanasov.bookshop.services;

import org.atanasov.bookshop.models.BookTitlePriceServiceModel;
import org.atanasov.bookshop.models.BookTitleServiceModel;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
  List<BookTitleServiceModel> findAllWithAgeRestriction(String ageRestrictionString);

  List<BookTitleServiceModel> findAllWithEditionAndCopiesLesThan(String editionString, int copies);

  List<BookTitlePriceServiceModel> findAllWithPriceBetweenDesc(BigDecimal min, BigDecimal max);

  List<BookTitlePriceServiceModel> findAllWithPriceBetween(
      BigDecimal min, BigDecimal max, Sort sort);
}
