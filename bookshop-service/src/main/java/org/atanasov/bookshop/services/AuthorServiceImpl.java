package org.atanasov.bookshop.services;

import org.atanasov.bookshop.core.repository.AuthorRepository;
import org.atanasov.bookshop.core.specifications.AuthorSpecifications;
import org.atanasov.bookshop.models.AuthorFullNameServiceModel;
import org.atanasov.bookshop.utils.ModelMapperWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {
  private final AuthorRepository authorRepository;
  private final ModelMapperWrapper modelMapper;

  public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapperWrapper modelMapper) {
    this.authorRepository = authorRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<AuthorFullNameServiceModel> findAllWithFirstNameEndingOn(String searchString) {
    return authorRepository.findAll(AuthorSpecifications.withFirstNameEnding(searchString)).stream()
        .map(author -> modelMapper.map(author, AuthorFullNameServiceModel.class))
        .collect(Collectors.toList());
  }
}
