package org.atanasov.bookshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Configuration
//@ComponentScan("org.atanasov.bookshop")
@EnableJpaAuditing
public class BookshopCoreConfiguration implements AuditorAware<String> {
  public static final String SYSTEM_ACCOUNT = "system";
  private static final Logger logger = LoggerFactory.getLogger(BookshopCoreConfiguration.class);


  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of(SYSTEM_ACCOUNT);
  }
  @PostConstruct
  public void postConstruct() {
    logger.info("BOOKSHOP CORE MODULE LOADED!");
  }
}
