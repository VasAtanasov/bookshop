package org.atanasov.bookshop.feature.common;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputReaderImpl implements InputReader {
  private final Scanner scanner;

  public InputReaderImpl() {
    this.scanner = new Scanner(System.in);
  }

  @Override
  public String readLine() {
    System.out.print("bookshop > ");
    return this.scanner.nextLine();
  }
}
