package org.atanasov.bookshop.core.domain.book;

import lombok.Getter;
import lombok.Setter;
import org.atanasov.bookshop.core.domain.BaseEntity;
import org.atanasov.bookshop.core.domain.category.Category;
import org.atanasov.bookshop.core.domain.author.Author;
import org.atanasov.bookshop.core.enums.AgeRestriction;
import org.atanasov.bookshop.core.enums.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book extends BaseEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Access(value = AccessType.PROPERTY)
  @Column(name = "id", nullable = false, updatable = false, unique = true)
  private Long id;

  @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "author_id",
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "fk_books_authors_id"))
  private Author author;

  @Column(name = "title", length = 50, nullable = false)
  private String title;

  @Column(name = "description", length = 1000)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "edition_type", nullable = false)
  private EditionType editionType;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Column(name = "copies", nullable = false)
  private int copies;

  @Column(name = "release_date")
    private LocalDate releaseDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "age_restriction", nullable = false)
  private AgeRestriction ageRestriction;

  @ManyToMany(
      fetch = FetchType.LAZY,
      cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,
      })
  @JoinTable(
      name = "books_categories",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
  private Set<Category> categories = new HashSet<>();
}
