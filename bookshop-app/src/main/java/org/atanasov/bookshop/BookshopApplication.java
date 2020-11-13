package org.atanasov.bookshop;

import org.atanasov.bookshop.feature.common.Engine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookshopApplication implements CommandLineRunner {

  private final Engine engine;

  public BookshopApplication(Engine engine) {
    this.engine = engine;
  }

  public static void main(String[] args) {
    SpringApplication.run(BookshopApplication.class, args);
  }

  @Override
  public void run(String... args) {
    engine.run();
  }
}
