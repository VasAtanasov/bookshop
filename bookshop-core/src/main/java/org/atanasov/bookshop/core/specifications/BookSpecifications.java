package org.atanasov.bookshop.core.specifications;

import org.atanasov.bookshop.core.domain.book.Book;
import org.atanasov.bookshop.core.domain.book.Book_;
import org.atanasov.bookshop.core.enums.AgeRestriction;
import org.atanasov.bookshop.core.enums.EditionType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class BookSpecifications {

  public static Specification<Book> booksForAgeRestriction(AgeRestriction ageRestriction) {
    return (root, query, cb) -> cb.equal(root.get(Book_.AGE_RESTRICTION), ageRestriction);
  }

  public static Specification<Book> booksForEdition(EditionType editionType) {
    return (root, query, cb) -> cb.equal(root.get(Book_.editionType), editionType);
  }

  public static Specification<Book> booksForEditionAndCopiesLessThan(
      EditionType editionType, int copies) {
    Specification<Book> withEditionType = booksForEdition(editionType);
    Specification<Book> withCopiesLessThan =
        (root, query, cb) -> cb.lessThan(root.get(Book_.copies), copies);
    return Specification.where(withEditionType).and(withCopiesLessThan);
  }

  public static Specification<Book> booksWithPriceBetween(BigDecimal min, BigDecimal max) {
    return (root, query, cb) -> cb.between(root.get(Book_.price), min, max);
  }
}
