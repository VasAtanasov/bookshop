package org.atanasov.bookshop.feature.error;

public class BookshopException extends RuntimeException {

  public BookshopException(ErrorModel error) {
    super(error.getMessage());
  }

  public BookshopException() {
    super("");
  }

  public BookshopException(String message) {
    super(message);
  }
}
