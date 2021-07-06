package com.rodrigosousa.bookstoremanager.service;

import com.rodrigosousa.bookstoremanager.dto.BookDTO;
import com.rodrigosousa.bookstoremanager.entity.Book;
import com.rodrigosousa.bookstoremanager.exception.BookNotFoundException;
import com.rodrigosousa.bookstoremanager.repository.BookRepository;
import com.rodrigosousa.bookstoremanager.utils.BookUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.rodrigosousa.bookstoremanager.utils.BookUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void whenGivenExistingIdThenReturnThisBook() throws BookNotFoundException {
        Book expectedFoundBook = BookUtils.createFakeBook();

        Mockito.when(bookRepository.findById(expectedFoundBook.getId())).thenReturn(Optional.of(expectedFoundBook));

        BookDTO bookDTO = bookService.findById(expectedFoundBook.getId());

        Assertions.assertEquals(expectedFoundBook.getName(), bookDTO.getName());
        Assertions.assertEquals(expectedFoundBook.getIsbn(), bookDTO.getIsbn());
        Assertions.assertEquals(expectedFoundBook.getPublisherName(), bookDTO.getPublisherName());
    }
    
    @Test
    void whenGivenUnexistingIdThenNotFindThrowAnException() {
    	var invalidId = 10L;
    	Mockito.when(bookRepository.findById(invalidId))
    		.thenReturn(Optional.ofNullable(any(Book.class)));
    	Assertions.assertThrows(BookNotFoundException.class, () -> bookService.findById(invalidId));
    }
}