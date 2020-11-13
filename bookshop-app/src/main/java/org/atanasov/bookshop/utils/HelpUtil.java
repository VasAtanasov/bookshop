package org.atanasov.bookshop.utils;

import org.atanasov.bookshop.feature.author.AuthorSubCommand;
import org.atanasov.bookshop.feature.common.SubCommand;

import java.util.Arrays;
import java.util.Set;

public class HelpUtil {

  public static <T extends SubCommand> String getHelpString(Set<T> suCommands, String name) {
    StringBuilder sb = new StringBuilder();
    sb.append("Main command: ");
    sb.append(name);
    sb.append(System.lineSeparator());
    sb.append("usage: ").append(name).append(" ").append(suCommands.toString());
    sb.append(" [space separated arguments]");
    sb.append(System.lineSeparator());
    for (T subCommand : suCommands) {
      sb.append("\t");
      sb.append(subCommand.getCmd().toLowerCase());
      for (int i = 0; i < subCommand.getArgsNames().length; i += 2) {
        sb.append(" ");
        sb.append(subCommand.getArgsNames()[i]);
        sb.append("(").append(subCommand.getArgsNames()[i + 1]).append(")");
      }
      sb.append(" - ");
      sb.append(subCommand.getDescription());
      sb.append(System.lineSeparator());
    }
    return sb.toString().trim();
  }
}
