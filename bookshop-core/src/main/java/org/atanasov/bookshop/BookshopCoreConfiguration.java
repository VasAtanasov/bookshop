package org.atanasov.bookshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;

@Configuration
@EnableJpaAuditing
public class BookshopCoreConfiguration {
  private static final Logger logger = LoggerFactory.getLogger(BookshopCoreConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    logger.info("BOOKSHOP CORE MODULE LOADED!");
  }
}
