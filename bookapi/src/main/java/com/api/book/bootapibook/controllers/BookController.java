package com.api.book.bootapibook.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootapibook.entities.Book;
import com.api.book.bootapibook.services.BookServices;

//@Controller
@RestController
public class BookController {
//	@RequestMapping(value="/getBooks", method = RequestMethod.GET)
//	@ResponseBody
	
	@Autowired
	private BookServices bookService;
	
	@GetMapping("/getbooks")
	public ArrayList<Book> getBooks() {
		
		// jackson auto converts objects into json while returning in body
		return bookService.getAllBooks();
	}
	
	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable("id") int id) {
		return bookService.getBookById(id);
	}
}
