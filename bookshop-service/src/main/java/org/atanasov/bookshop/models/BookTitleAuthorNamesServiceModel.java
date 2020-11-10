package org.atanasov.bookshop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookTitleAuthorNamesServiceModel {
  private String authorFirstName;
  private String authorLastName;
  private String title;

  @Override
  public String toString() {
    return String.format("%s (%s %s)", title, authorFirstName, authorLastName);
  }
}
