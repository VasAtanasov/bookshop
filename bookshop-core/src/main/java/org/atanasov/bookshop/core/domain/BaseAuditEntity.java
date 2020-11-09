package org.atanasov.bookshop.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditEntity<T extends Serializable> extends BaseEntity<T>
    implements Serializable {

  @CreatedDate
//  @NotNull
  @PastOrPresent
  @Column(name = "created_at")
  protected LocalDateTime createdAt;

  @LastModifiedDate
//  @NotNull
  @Column(name = "modified_at")
  protected LocalDateTime modifiedAt;

  @Column(name = "deleted_at")
  protected LocalDateTime deletedAt;

  @CreatedBy
  @Column(name = "created_by")
  private String createdBy;

  @LastModifiedBy
  @Column(name = "modified_by")
  private String modifiedBy;

  @Column(name = "deleted_by")
  private String deletedBy;
}
