package org.atanasov.bookshop.core.dto;

import lombok.Getter;
import org.atanasov.bookshop.core.domain.book.Book;

public class BookTitleDTO {
  @Getter private String title;

  private BookTitleDTO(String title) {
    this.title = title;
  }

  public static BookTitleDTO of(Book book) {
    return new BookTitleDTO(book.getTitle());
  }

  @Override
  public String toString() {
    return "BookTitleDTO{" + "title='" + title + '\'' + '}';
  }
}
