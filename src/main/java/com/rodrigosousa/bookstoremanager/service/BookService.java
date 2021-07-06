package com.rodrigosousa.bookstoremanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigosousa.bookstoremanager.dto.BookDTO;
import com.rodrigosousa.bookstoremanager.dto.MessageResponseDTO;
import com.rodrigosousa.bookstoremanager.entity.Author;
import com.rodrigosousa.bookstoremanager.entity.Book;
// import com.rodrigosousa.bookstoremanager.mapper.BookMapper;
import com.rodrigosousa.bookstoremanager.repository.BookRepository;

@Service
public class BookService {

	private BookRepository bookRepository;
	
	// private final BookMapper bookMapper = BookMapper.INSTANCE;
	
	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public MessageResponseDTO create(BookDTO bookDTO) {
		Author authorToSave = Author.builder()
				.name(bookDTO.getAuthor().getName())
				.age(bookDTO.getAuthor().getAge())
				.build();
		Book bookToSave = Book.builder() // bookMapper.toModel(bookDTO)
				.name(bookDTO.getName())
				.pages(bookDTO.getPages())
				.chapters(bookDTO.getChapters())
				.isbn(bookDTO.getIsbn())
				.publisherName(bookDTO.getPublisherName())
				.author(authorToSave)
				.build();
		Book savedBook = bookRepository.save(bookToSave);
		return MessageResponseDTO.builder()
				.message("Book Created with ID " + savedBook.getId())
				.build();		
	}
}
