package org.atanasov.bookshop;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "org.atanasov.bookshop")
public class BookshopServiceConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(BookshopServiceConfiguration.class);

  @PostConstruct
  public void postConstruct() {
    logger.info("BOOKSHOP SERVICE MODULE LOADED!");
  }
}
