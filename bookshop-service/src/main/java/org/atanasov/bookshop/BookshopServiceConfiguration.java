package org.atanasov.bookshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class BookshopServiceConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(BookshopServiceConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    logger.info("BOOKSHOP SERVICE MODULE LOADED!");
  }
}
