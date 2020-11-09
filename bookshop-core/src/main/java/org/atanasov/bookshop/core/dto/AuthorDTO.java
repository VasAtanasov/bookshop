package org.atanasov.bookshop.core.dto;

import lombok.Getter;
import org.atanasov.bookshop.core.domain.author.Author;

public class AuthorDTO {
  @Getter private final String lastName;
  @Getter private final String firstName;

  private AuthorDTO(String lastName, String firstName) {
    this.lastName = lastName;
    this.firstName = firstName;
  }

  public static AuthorDTO of(Author author) {
    return new AuthorDTO(author.getFirstName(), author.getLastName());
  }
}
