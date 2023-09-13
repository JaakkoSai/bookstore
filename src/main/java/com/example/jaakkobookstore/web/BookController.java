package com.example.jaakkobookstore.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
}
