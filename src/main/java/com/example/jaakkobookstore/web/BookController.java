package com.example.jaakkobookstore.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

	    @RequestMapping(value = "/index", method = RequestMethod.GET)
	    public String index(Model model) {
	        
	        return "index"; 
	    }
	}


