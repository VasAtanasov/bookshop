package org.atanasov.bookshop.services;

import org.atanasov.bookshop.models.*;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

  long booksCountForTitleLength(int length);

  Optional<ReducedBookServiceModel> findBookByTitle(String title);

  int updateBookCopiesAfterDate(LocalDate after, int copies);

  int deleteBooksWithCopiesLessThan(int copies);
}
