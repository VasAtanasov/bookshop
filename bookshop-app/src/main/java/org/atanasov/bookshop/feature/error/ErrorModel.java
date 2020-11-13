package org.atanasov.bookshop.feature.error;

import lombok.Builder;

@Builder
public class ErrorModel {
  private static final String PREFIX = "[error] ";
  private final String message;

  public ErrorModel(String message) {
    this.message = message;
  }

  public String getMessage() {
    return PREFIX + message;
  }
}
