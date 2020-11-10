package org.atanasov.bookshop.core.specifications;

import org.atanasov.bookshop.core.domain.author.Author;
import org.atanasov.bookshop.core.domain.author.Author_;
import org.atanasov.bookshop.core.domain.book.Book;
import org.atanasov.bookshop.core.domain.book.Book_;
import org.atanasov.bookshop.core.enums.AgeRestriction;
import org.atanasov.bookshop.core.enums.EditionType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookSpecifications {

  public static Specification<Book> forAgeRestriction(AgeRestriction ageRestriction) {
    return (root, query, cb) -> cb.equal(root.get(Book_.AGE_RESTRICTION), ageRestriction);
  }

  public static Specification<Book> forEdition(EditionType editionType) {
    return (root, query, cb) -> cb.equal(root.get(Book_.editionType), editionType);
  }

  public static Specification<Book> forEditionAndCopiesLessThan(
      EditionType editionType, int copies) {
    Specification<Book> withEditionType = forEdition(editionType);
    Specification<Book> withCopiesLessThan =
        (root, query, cb) -> cb.lessThan(root.get(Book_.copies), copies);
    return Specification.where(withEditionType).and(withCopiesLessThan);
  }

  public static Specification<Book> forPriceBetween(BigDecimal min, BigDecimal max) {
    return (root, query, cb) -> cb.between(root.get(Book_.price), min, max);
  }

  public static Specification<Book> forReleasedBeforeDate(LocalDate before) {
    return (root, query, cb) -> cb.lessThan(root.get(Book_.releaseDate), before);
  }

  public static Specification<Book> forTitleContaining(String searchString) {
    return (root, query, cb) ->
        cb.like(cb.lower(root.get(Book_.title)), "%" + searchString.toLowerCase() + "%");
  }

  public static Specification<Book> forAuthorLastNameStarting(String searchString) {
    return (root, query, cb) -> {
      Join<Book, Author> booksAuthor = root.join(Book_.author, JoinType.INNER);
      return cb.like(cb.lower(booksAuthor.get(Author_.lastName)), searchString.toLowerCase() + "%");
    };
  }

  public static Specification<Book> forCountWithTitleLength(int length) {
    return (root, query, cb) -> cb.greaterThan(cb.length(root.get(Book_.title)), length);
  }
}
