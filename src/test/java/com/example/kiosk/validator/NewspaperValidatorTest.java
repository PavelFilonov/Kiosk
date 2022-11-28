package com.example.kiosk.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.exception.NewspaperException;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NewspaperValidatorTest {

	private NewspaperValidator newspaperValidator;

	private Subject newspaper;

	@BeforeEach
	void setUp() {
		newspaperValidator = new NewspaperValidator();
		newspaper = Subject.builder()
				.number(1)
				.dateRelease(LocalDate.of(2022, 1, 1))
				.build();
	}

	@Test
	void validateBook() {
		assertDoesNotThrow(() -> newspaperValidator.validateNewspaper(newspaper));
	}

	@Test
	void validateBookWhenNumberIsTooShort() {
		newspaper.setNumber(0);

		assertThatThrownBy(() -> newspaperValidator.validateNewspaper(newspaper))
				.isExactlyInstanceOf(NewspaperException.class);
	}

	@Test
	void validateBookWhenDateReleaseIsAfterThanNow() {
		newspaper.setDateRelease(LocalDate.MAX);

		assertThatThrownBy(() -> newspaperValidator.validateNewspaper(newspaper))
				.isExactlyInstanceOf(NewspaperException.class);
	}

}
