package org.atanasov.bookshop.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorBooksCountServiceModel {
  private String firstName;
  private String lastName;
  private long booksCount;

  @Override
  public String toString() {
    return String.format("%s %s - %d books", this.firstName, this.lastName, this.booksCount);
  }
}
