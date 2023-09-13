package com.example.jaakkobookstore.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Autowired
    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        // Insert example data
        Book book1 = new Book("Sample Book 1", "Author 1", 2022, "ISBN123", 29.99);
        Book book2 = new Book("Sample Book 2", "Author 2", 2021, "ISBN456", 19.99);

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}

