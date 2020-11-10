package org.atanasov.bookshop.services;

import org.atanasov.bookshop.core.repository.AuthorRepository;
import org.atanasov.bookshop.utils.ModelMapperWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {
  private final AuthorRepository authorRepository;
  private final ModelMapperWrapper modelMapper;

  public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapperWrapper modelMapper) {
    this.authorRepository = authorRepository;
    this.modelMapper = modelMapper;
  }


}
