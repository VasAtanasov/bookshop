package org.atanasov.bookshop.core.specifications;

import org.atanasov.bookshop.core.domain.author.Author;
import org.atanasov.bookshop.core.domain.author.Author_;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecifications {

  public static Specification<Author> withFirstNameEnding(String searchString) {
    return (root, query, cb) -> cb.like(root.get(Author_.firstName), "%" + searchString);
  }
}
