package org.atanasov.bookshop.core.domain.author;

import lombok.Getter;
import lombok.Setter;
import org.atanasov.bookshop.core.domain.BaseEntity;
import org.atanasov.bookshop.core.domain.book.Book;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "authors")
@NamedStoredProcedureQuery(
    name = Author.QUERY_AUTHOR_BOOKS_COUNT_BY_NAMES,
    procedureName = "udp_get_author_books_count",
    parameters = {
      @StoredProcedureParameter(name = "first_name", type = String.class),
      @StoredProcedureParameter(name = "last_name", type = String.class)
    })
public class Author extends BaseEntity<Long> {
  public static final String QUERY_AUTHOR_BOOKS_COUNT_BY_NAMES = "Author.getBooksCountForAuthor";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Access(value = AccessType.PROPERTY)
  @Column(name = "id", nullable = false, updatable = false, unique = true)
  private Long id;

  @Size(max = 50)
  @Column(name = "first_name")
  private String firstName;

  @Size(max = 50)
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @OneToMany(
      mappedBy = "author",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<Book> books = new ArrayList<>();
}
