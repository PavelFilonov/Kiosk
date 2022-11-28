package com.example.kiosk.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.exception.BookException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookValidatorTest {

	private BookValidator bookValidator;

	private Subject book;

	@BeforeEach
	void setUp() {
		bookValidator = new BookValidator();
		book = Subject.builder()
				.author("Author")
				.publisher("Publisher")
				.numberPage(3)
				.build();
	}

	@Test
	void validateBook() {
		assertDoesNotThrow(() -> bookValidator.validateBook(book));
	}

	@Test
	void validateBookWhenAuthorIsNull() {
		book.setAuthor(null);

		assertThatThrownBy(() -> bookValidator.validateBook(book))
				.isExactlyInstanceOf(BookException.class);
	}

	@Test
	void validateBookWhenAuthorIsBlank() {
		book.setAuthor(" ");

		assertThatThrownBy(() -> bookValidator.validateBook(book))
				.isExactlyInstanceOf(BookException.class);
	}

	@Test
	void validateBookWhenAuthorIsTooShort() {
		book.setAuthor("1");

		assertThatThrownBy(() -> bookValidator.validateBook(book))
				.isExactlyInstanceOf(BookException.class);
	}

	@Test
	void validateBookWhenAuthorIsTooLong() {
		book.setAuthor("11111111111111111111111111111111111111111111111111");

		assertThatThrownBy(() -> bookValidator.validateBook(book))
				.isExactlyInstanceOf(BookException.class);
	}

	@Test
	void validateBookWhenPublisherIsNull() {
		book.setPublisher(null);

		assertThatThrownBy(() -> bookValidator.validateBook(book))
				.isExactlyInstanceOf(BookException.class);
	}

	@Test
	void validateBookWhenPublisherIsBlank() {
		book.setPublisher(" ");

		assertThatThrownBy(() -> bookValidator.validateBook(book))
				.isExactlyInstanceOf(BookException.class);
	}

	@Test
	void validateBookWhenPublisherIsTooShort() {
		book.setPublisher("1");

		assertThatThrownBy(() -> bookValidator.validateBook(book))
				.isExactlyInstanceOf(BookException.class);
	}

	@Test
	void validateBookWhenPublisherIsTooLong() {
		book.setPublisher("11111111111111111111111111111111111111111111111111");

		assertThatThrownBy(() -> bookValidator.validateBook(book))
				.isExactlyInstanceOf(BookException.class);
	}

	@Test
	void validateBookWhenNumberPageIsTooShort() {
		book.setNumberPage(0);

		assertThatThrownBy(() -> bookValidator.validateBook(book))
				.isExactlyInstanceOf(BookException.class);
	}

}
