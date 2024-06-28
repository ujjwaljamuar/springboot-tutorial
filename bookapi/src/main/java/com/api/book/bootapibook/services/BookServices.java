package com.api.book.bootapibook.services;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.api.book.bootapibook.entities.Book;

@Component
public class BookServices {
	private static ArrayList<Book> bookList = new ArrayList<>();
	
	// static block always executed first, 
	// so that our controller always return atleast one object
	static {
		bookList.add(new Book(0, "Meena", "Luffy"));
		bookList.add(new Book(1, "Meena1", "Luffy1"));
		bookList.add(new Book(2, "Meena2", "Luffy2"));
	}
	
	public ArrayList<Book> getAllBooks(){
		return bookList;
	}
	
	public Book getBookById(int id) {
		Book book = null;
		book = bookList.stream().filter(e -> e.getId() == id).findFirst().get();
		
		return book;
	}
}
