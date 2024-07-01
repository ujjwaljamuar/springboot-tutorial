package com.api.book.bootapibook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.book.bootapibook.entities.Book;

@Component
public class BookServices {
	private static List<Book> bookList = new ArrayList<>();

	// static block always executed first,
	// so that our controller always return atleast one object
	static {
		bookList.add(new Book(0, "Meena", "Luffy"));
		bookList.add(new Book(1, "Meena1", "Luffy1"));
		bookList.add(new Book(2, "Meena2", "Luffy2"));
	}

	// get all books
	public List<Book> getAllBooks() {
		return bookList;
	}

	// get book by id
	public Book getBookById(int id) {
		Book book = null;
		try {
			book = bookList.stream().filter(e -> e.getId() == id).findFirst().get();

			return book;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return book;
	}

	// adding new book
	public Book addBook(Book newBook) {
		bookList.add(newBook);

		return newBook;
	}

	public Book deleteBookById(int id) {
//		Book book = bookList.stream().filter(e -> e.getId() == id).findFirst().get();
//		bookList.remove(book);

		Book b = getBookById(id);

		// or change the whole booklist
		bookList = bookList.stream().filter(book -> book.getId() != id).collect(Collectors.toList());

		return b;
	}

	public Book updateBookById(int id, Book newBookObj) {
		bookList = bookList.stream().map(book -> {
			if (book.getId() == id) {
				book.setTitle(newBookObj.getTitle());
				book.setAuthor(newBookObj.getAuthor());
			}
			return book;
		}).collect(Collectors.toList());

		return newBookObj;
	}
}
