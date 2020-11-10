package org.atanasov.bookshop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.bookshop.core.domain.book.Book;

@Getter
@Setter
@NoArgsConstructor
public class BookTitleServiceModel {
  private String title;

  private BookTitleServiceModel(String title) {
    this.title = title;
  }

  public static BookTitleServiceModel of(Book book) {
    return new BookTitleServiceModel(book.getTitle());
  }

  @Override
  public String toString() {
    return this.title;
  }
}
