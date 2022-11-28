package com.example.kiosk.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.exception.SubjectException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubjectValidatorTest {

	private SubjectValidator subjectValidator;

	private Subject subject;

	@BeforeEach
	void setUp() {
		subjectValidator = new SubjectValidator();
		subject = Subject.builder()
				.title("Title")
				.amount(5)
				.build();
	}

	@Test
	void validateSubject() {
		assertDoesNotThrow(() -> subjectValidator.validateSubject(subject));
	}

	@Test
	void validateBookWhenTitleIsNull() {
		subject.setTitle(null);

		assertThatThrownBy(() -> subjectValidator.validateSubject(subject))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@Test
	void validateBookWhenTitleIsBlank() {
		subject.setTitle(" ");

		assertThatThrownBy(() -> subjectValidator.validateSubject(subject))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@Test
	void validateBookWhenAmountIsNegative() {
		subject.setAmount(-1);

		assertThatThrownBy(() -> subjectValidator.validateSubject(subject))
				.isExactlyInstanceOf(SubjectException.class);
	}

}