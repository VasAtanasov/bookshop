package org.atanasov.bookshop.feature.author;

import org.atanasov.bookshop.feature.common.Command;
import org.atanasov.bookshop.services.AuthorService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorCommand implements Command {
  private final AuthorService authorService;

  public AuthorCommand(AuthorService authorService) {
    this.authorService = authorService;
  }

  @Override
  public String execute(List<String> arguments) {
    return null;
  }
}
