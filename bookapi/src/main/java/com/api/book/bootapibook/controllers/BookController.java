package com.api.book.bootapibook.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<List<Book>> getBooks() {

		// jackson auto converts objects into json while returning in body
		List<Book> allBooks= bookService.getAllBooks();
		if(allBooks.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.of(Optional.of(allBooks));
	}

	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book =  bookService.getBookById(id);
		
		if(book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.of(Optional.of(book));
	}

	@PostMapping("book/new")
	// whatever json data you put in post request body will go in Book object
	public Book addBook(@RequestBody Book newBook) {
		Book addedBook = bookService.addBook(newBook);

		return addedBook;
	}

	@DeleteMapping("/book/{id}")
	public Book deleteBook(@PathVariable("id") int id) {
		Book deletedBook = bookService.deleteBookById(id);

		return deletedBook;
	}

	@PutMapping("book/{id}")
	public Book updateBook(@PathVariable("id") int id, @RequestBody Book newBook) {
		Book updatedBook = bookService.updateBookById(id, newBook);

		return updatedBook;
	}

}
