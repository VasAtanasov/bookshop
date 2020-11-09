package org.atanasov.bookshop.core.domain.category;

import lombok.Getter;
import lombok.Setter;
import org.atanasov.bookshop.core.domain.BaseAuditEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseAuditEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Access(value = AccessType.PROPERTY)
  @Column(name = "id", nullable = false, updatable = false, unique = true)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;
}
