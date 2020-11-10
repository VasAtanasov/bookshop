package org.atanasov.bookshop.services;

import org.atanasov.bookshop.core.enums.AgeRestriction;
import org.atanasov.bookshop.core.enums.EditionType;
import org.atanasov.bookshop.core.repository.BookRepository;
import org.atanasov.bookshop.core.specifications.BookSpecifications;
import org.atanasov.bookshop.models.BookServiceModel;
import org.atanasov.bookshop.models.BookTitleAuthorNamesServiceModel;
import org.atanasov.bookshop.models.BookTitlePriceServiceModel;
import org.atanasov.bookshop.models.BookTitleServiceModel;
import org.atanasov.bookshop.utils.EnumUtils;
import org.atanasov.bookshop.utils.ModelMapperWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
  private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

  private final BookRepository bookRepository;
  private final ModelMapperWrapper modelMapper;

  public BookServiceImpl(BookRepository bookRepository, ModelMapperWrapper modelMapper) {
    this.bookRepository = bookRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<BookTitleServiceModel> findAllWithAgeRestriction(String ageRestrictionString) {
    Objects.requireNonNull(ageRestrictionString, "Age restriction is required.");
    AgeRestriction ageRestriction =
        EnumUtils.fromString(ageRestrictionString, AgeRestriction.class).orElse(null);

    if (ageRestriction == null) {
      logger.error("Invalid age restriction: " + ageRestrictionString);
      return List.of();
    }

    return bookRepository.findAll(BookSpecifications.forAgeRestriction(ageRestriction)).stream()
        .map(book -> modelMapper.map(book, BookTitleServiceModel.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<BookTitleServiceModel> findAllWithEditionAndCopiesLesThan(
      String editionString, int copies) {
    Objects.requireNonNull(editionString, "Edition type is required.");
    EditionType editionType = EnumUtils.fromString(editionString, EditionType.class).orElse(null);

    if (editionType == null) {
      logger.error("Invalid edition type: " + editionString);
      return List.of();
    }

    if (copies <= 0) {
      logger.error("Copies must be positive number: " + editionString);
      return List.of();
    }

    return bookRepository
        .findAll(BookSpecifications.forEditionAndCopiesLessThan(editionType, copies))
        .stream()
        .map(book -> modelMapper.map(book, BookTitleServiceModel.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<BookTitlePriceServiceModel> findAllWithPriceBetweenDesc(
      BigDecimal min, BigDecimal max) {
    return findAllWithPriceBetween(min, max, Sort.by(Sort.Direction.DESC, "price"));
  }

  @Override
  public List<BookTitlePriceServiceModel> findAllWithPriceBetween(
      BigDecimal min, BigDecimal max, Sort sort) {
    return bookRepository.findAll(BookSpecifications.forPriceBetween(min, max), sort).stream()
        .map(book -> modelMapper.map(book, BookTitlePriceServiceModel.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<BookTitleServiceModel> findAllNotReleased(int year) {
    return bookRepository.findAllByReleaseDateNotLine(2000).stream()
        .map(book -> modelMapper.map(book, BookTitleServiceModel.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<BookServiceModel> findAllWithReleaseDateBefore(LocalDate before) {
    return bookRepository.findAll(BookSpecifications.forReleasedBeforeDate(before)).stream()
        .map(
            book ->
                BookServiceModel.builder()
                    .title(book.getTitle())
                    .editionType(book.getEditionType())
                    .price(book.getPrice())
                    .build())
        .collect(Collectors.toList());
  }

  @Override
  public List<BookTitleServiceModel> findAllWithTittleContaining(String searchString) {
    Objects.requireNonNull(searchString, "Invalid search string for book title.");
    return bookRepository.findAll(BookSpecifications.forTitleContaining(searchString)).stream()
        .map(book -> modelMapper.map(book, BookTitleServiceModel.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<BookTitleAuthorNamesServiceModel> findAllWithAuthorLastNameStarting(
      String searchString) {
    Objects.requireNonNull(searchString, "Invalid search string for book's author last name.");
    return bookRepository
        .findAll(BookSpecifications.forAuthorLastNameStarting(searchString))
        .stream()
        .map(book -> modelMapper.map(book, BookTitleAuthorNamesServiceModel.class))
        .collect(Collectors.toList());
  }

  @Override
  public long booksCountForTitleLength(int length) {
    return bookRepository.findAll(BookSpecifications.forCountWithTitleLength(length)).size();
  }
}
