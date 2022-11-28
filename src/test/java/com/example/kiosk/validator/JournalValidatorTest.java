package com.example.kiosk.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.exception.JournalException;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JournalValidatorTest {

	private JournalValidator journalValidator;

	private Subject journal;

	@BeforeEach
	void setUp() {
		journalValidator = new JournalValidator();
		journal = Subject.builder()
				.number(1)
				.dateRelease(LocalDate.of(2022, 1, 1))
				.numberPage(3)
				.build();
	}

	@Test
	void validateBook() {
		assertDoesNotThrow(() -> journalValidator.validateJournal(journal));
	}

	@Test
	void validateBookWhenNumberIsTooShort() {
		journal.setNumber(0);

		assertThatThrownBy(() -> journalValidator.validateJournal(journal))
				.isExactlyInstanceOf(JournalException.class);
	}

	@Test
	void validateBookWhenDateReleaseIsAfterThanNow() {
		journal.setDateRelease(LocalDate.MAX);

		assertThatThrownBy(() -> journalValidator.validateJournal(journal))
				.isExactlyInstanceOf(JournalException.class);
	}

	@Test
	void validateBookWhenNumberPageIsTooShort() {
		journal.setNumberPage(0);

		assertThatThrownBy(() -> journalValidator.validateJournal(journal))
				.isExactlyInstanceOf(JournalException.class);
	}

}
