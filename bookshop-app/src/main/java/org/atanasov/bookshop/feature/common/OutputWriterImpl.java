package org.atanasov.bookshop.feature.common;

import org.springframework.stereotype.Component;

@Component
public class OutputWriterImpl implements OutputWriter {

  @Override
  public void write(String output) {
    System.out.print(output);
  }

  @Override
  public void writeLine(String output) {
    write("bookshop > ");
    System.out.println(output);
  }

  @Override
  public void writeLine(Object output) {
    String str = String.valueOf(output);
    writeLine(str);
  }
}
