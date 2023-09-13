package com.example.jaakkobookstore.web;

import java.util.ArrayList;
import java.util.List;
import com.example.jaakkobookstore.domain.Book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jaakkobookstore.domain.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class BookController {

	private final BookRepository bookRepository;

	@Autowired
	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
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
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    // Delete functionality
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }
}
