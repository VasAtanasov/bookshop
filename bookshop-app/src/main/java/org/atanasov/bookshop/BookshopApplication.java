package org.atanasov.bookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // (scanBasePackages = "org.atanasov.bookshop")
// @EnableBookshopServiceModule
// @EnableBookshopCoreModule
public class BookshopApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookshopApplication.class, args);
  }
}
