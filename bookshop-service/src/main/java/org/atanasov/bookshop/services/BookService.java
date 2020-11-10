package org.atanasov.bookshop.services;

import org.atanasov.bookshop.models.BookServiceModel;
import org.atanasov.bookshop.models.BookTitleAuthorNamesServiceModel;
import org.atanasov.bookshop.models.BookTitlePriceServiceModel;
import org.atanasov.bookshop.models.BookTitleServiceModel;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
  List<BookTitleServiceModel> findAllWithAgeRestriction(String ageRestrictionString);

  List<BookTitleServiceModel> findAllWithEditionAndCopiesLesThan(String editionString, int copies);

  List<BookTitlePriceServiceModel> findAllWithPriceBetweenDesc(BigDecimal min, BigDecimal max);

  List<BookTitlePriceServiceModel> findAllWithPriceBetween(
      BigDecimal min, BigDecimal max, Sort sort);

  List<BookTitleServiceModel> findAllNotReleased(int year);

  List<BookServiceModel> findAllWithReleaseDateBefore(LocalDate before);

  List<BookTitleServiceModel> findAllWithTittleContaining(String searchString);

  List<BookTitleAuthorNamesServiceModel> findAllWithAuthorLastNameStarting(String searchString);
}
