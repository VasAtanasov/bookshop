package org.atanasov.bookshop;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class BookshopServiceConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(BookshopServiceConfiguration.class);

  private static final ModelMapper mapper;

  static {
    mapper = new ModelMapper();
  }

  @Bean
  public ModelMapper modelMapper() {
    mapper
        .getConfiguration()
        .setAmbiguityIgnored(true)
        .setFieldMatchingEnabled(true);
    return mapper;
  }

  @PostConstruct
  public void postConstruct() {
    logger.info("BOOKSHOP SERVICE MODULE LOADED!");
  }
}
