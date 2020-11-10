package org.atanasov.bookshop.services;

import org.atanasov.bookshop.models.AuthorBooksCountServiceModel;
import org.atanasov.bookshop.models.AuthorFullNameServiceModel;

import java.util.List;

public interface AuthorService {
  List<AuthorFullNameServiceModel> findAllWithFirstNameEndingOn(String searchString);

  List<AuthorBooksCountServiceModel> aggregatedUserDetails();
}
