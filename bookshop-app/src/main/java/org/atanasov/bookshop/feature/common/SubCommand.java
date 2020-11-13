package org.atanasov.bookshop.feature.common;

public interface SubCommand {
  String getCmd();

  String getDescription();

  int getNArgs();

  String[] getArgsNames();
}
