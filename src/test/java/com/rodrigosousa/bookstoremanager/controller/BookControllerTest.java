package com.rodrigosousa.bookstoremanager.controller;

import org.hamcrest.core.Is;
import org.junit.gen5.api.BeforeEach;
import org.junit.gen5.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.rodrigosousa.bookstoremanager.dto.BookDTO;
import com.rodrigosousa.bookstoremanager.dto.MessageResponseDTO;
import com.rodrigosousa.bookstoremanager.service.BookService;
import com.rodrigosousa.bookstoremanager.utils.BookUtils;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	public static final String BOOK_API_URL_PATH = "/api/v1/books";
	
	private MockMvc mockMvc;
	
	@Mock
	private BookService bookService;
	
	@InjectMocks
	private BookController bookController;
	
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
				.build();
	}
	
	@Test
	void testWhenPOSTisCalledABookShouldBeCreated() throws Exception {
		BookDTO bookDTO = BookUtils.createFakeBookDTO();
		MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
				.message("Book created with ID " + bookDTO.getId())
				.build();
		
		Mockito.when(bookService.create(bookDTO)).thenReturn(expectedMessageResponse);
		
		mockMvc.perform(MockMvcRequestBuilders.post(BOOK_API_URL_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(BookUtils.asJsonString(bookDTO)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));
	}
	
    @Test
    void testWhenPOSTwithInvalidISBNIsCalledThenBadRequestShouldBeReturned() throws Exception {
        BookDTO bookDTO = BookUtils.createFakeBookDTO();
        bookDTO.setIsbn("invalid isbn");

        mockMvc.perform(MockMvcRequestBuilders.post(BOOK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(BookUtils.asJsonString(bookDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
