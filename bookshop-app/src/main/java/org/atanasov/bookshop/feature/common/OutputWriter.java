package org.atanasov.bookshop.feature.common;

public interface OutputWriter {
  void write(String output);

  void writeLine(String output);

  void writeLine(Object output);
}
