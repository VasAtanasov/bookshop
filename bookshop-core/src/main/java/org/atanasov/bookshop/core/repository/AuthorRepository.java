package org.atanasov.bookshop.core.repository;

import org.atanasov.bookshop.core.domain.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository
    extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {}
