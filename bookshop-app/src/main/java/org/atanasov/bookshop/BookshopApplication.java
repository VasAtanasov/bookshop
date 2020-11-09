package org.atanasov.bookshop;

import org.atanasov.bookshop.core.domain.author.Author;
import org.atanasov.bookshop.core.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookshopApplication implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookshopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (Author author : authorRepository.findAll()) {
            System.out.println(author.getId() + " " + author.getFirstName() + " " + author.getLastName());
        }
    }
}
