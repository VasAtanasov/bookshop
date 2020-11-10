package org.atanasov.bookshop.services;

import org.atanasov.bookshop.core.domain.author.Author;
import org.atanasov.bookshop.core.domain.author.Author_;
import org.atanasov.bookshop.core.domain.book.Book;
import org.atanasov.bookshop.core.domain.book.Book_;
import org.atanasov.bookshop.core.repository.AuthorRepository;
import org.atanasov.bookshop.core.specifications.AuthorSpecifications;
import org.atanasov.bookshop.models.AuthorBooksCountServiceModel;
import org.atanasov.bookshop.models.AuthorFullNameServiceModel;
import org.atanasov.bookshop.utils.ModelMapperWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {
  private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

  private final AuthorRepository authorRepository;
  private final ModelMapperWrapper modelMapper;
  private final EntityManager em;

  public AuthorServiceImpl(
      AuthorRepository authorRepository, ModelMapperWrapper modelMapper, EntityManager em) {
    this.authorRepository = authorRepository;
    this.modelMapper = modelMapper;
    this.em = em;
  }

  @Override
  public List<AuthorFullNameServiceModel> findAllWithFirstNameEndingOn(String searchString) {
    return authorRepository.findAll(AuthorSpecifications.withFirstNameEnding(searchString)).stream()
        .map(author -> modelMapper.map(author, AuthorFullNameServiceModel.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<AuthorBooksCountServiceModel> authorTotalBookCopies() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Tuple> query = cb.createTupleQuery();
    Root<Book> root = query.from(Book.class);

    Join<Book, Author> booksAuthor = root.join(Book_.author, JoinType.INNER);

    query.multiselect(
        booksAuthor.get(Author_.firstName),
        booksAuthor.get(Author_.lastName),
        cb.sum(root.get(Book_.copies)));

    query.groupBy(booksAuthor.get(Author_.firstName), booksAuthor.get(Author_.lastName));
    query.orderBy(cb.desc(cb.sum(root.get(Book_.copies))));
    query.distinct(true);

    List<Tuple> results = em.createQuery(query).getResultList();
    logger.info("results of query: {}", results);

    return results.stream()
        .map(
            tuple ->
                AuthorBooksCountServiceModel.builder()
                    .firstName(tuple.get(0, String.class))
                    .lastName(tuple.get(1, String.class))
                    .booksCount(tuple.get(2, Long.class))
                    .build())
        .collect(Collectors.toList());
  }


}
