package com.example.kiosk.validator;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.exception.BookException;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class BookValidator {

	public void validateBook(Subject book) throws BookException {
		validateAuthor(book.getAuthor());
		validatePublisher(book.getPublisher());
		validateNumberPage(book.getNumberPage());
	}

	private void validateAuthor(String author) throws BookException {
		if (Objects.isNull(author) || author.isBlank()) {
			throw new BookException("Blank author");
		}
		if (author.length() < 2 || author.length() > 30) {
			throw new BookException("Author should be from 1 to 30 symbols");
		}
	}

	private void validatePublisher(String publisher) throws BookException {
		if (Objects.isNull(publisher) || publisher.isBlank()) {
			throw new BookException("Blank publisher");
		}
		if (publisher.length() < 2 || publisher.length() > 30) {
			throw new BookException("Publisher should be from 1 to 30 symbols");
		}
	}

	private void validateNumberPage(int numberPage) throws BookException {
		if (numberPage < 1) {
			throw new BookException("Number page can`t be negative or zero");
		}
	}

}
