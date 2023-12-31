package com.example.jaakkobookstore.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.jaakkobookstore.domain.Book;
import com.example.jaakkobookstore.domain.Category;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.example.jaakkobookstore.domain.BookRepository;
import com.example.jaakkobookstore.domain.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@Controller
public class BookController {

	private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;

	@Autowired
	public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
		this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {

		return "index";
	}

	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		
		List<Book> books = bookRepository.findAll();

		model.addAttribute("books", books);
		
		return "booklist";
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String showAddBookForm(Model model) {
		List<Category> categories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categories);
        return "addbook";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }
    

    @RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book id"));
        model.addAttribute("book", book);
        return "editbook";
    }

    @RequestMapping(value = "/editbook/{id}", method = RequestMethod.POST)
    public String editBook(@PathVariable Long id, @ModelAttribute Book updatedBook) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book id"));
        
      
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPublicationYear(updatedBook.getPublicationYear());
        book.setIsbn(updatedBook.getIsbn());
        book.setPrice(updatedBook.getPrice());
        
        bookRepository.save(book);
        return "redirect:/booklist";
    }
    
    @RestController
    @RequestMapping("/api/books")
    public class BookRestController {
    	private final BookRepository bookRepository;

        public BookRestController(BookRepository bookRepository) {
            this.bookRepository = bookRepository;
    }
        @GetMapping
        public Iterable<Book> getAllBooks() {
            return bookRepository.findAll();
        }
        
        @GetMapping("/{id}")
        public ResponseEntity<Book> getBookById(@PathVariable Long id) {
            Optional<Book> book = bookRepository.findById(id);
            if (book.isPresent()) {
                return ResponseEntity.ok(book.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    }


}

