package com.example.kiosk.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.example.kiosk.exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserValidatorTest {

	private UserValidator userValidator;

	@BeforeEach
	void setUp() {
		userValidator = new UserValidator();
	}

	@Test
	void validateUsername() {
		assertDoesNotThrow(() -> userValidator.validateUsername("user name"));
	}

	@Test
	void validateWhenUsernameIsNull() {
		assertThatThrownBy(() -> userValidator.validateUsername(null))
				.isExactlyInstanceOf(UserException.class);
	}

	@Test
	void validateWhenUsernameIsBlank() {
		assertThatThrownBy(() -> userValidator.validateUsername(" "))
				.isExactlyInstanceOf(UserException.class);
	}

	@Test
	void validateWhenUsernameLessThanMin() {
		assertThatThrownBy(() -> userValidator.validateUsername("1"))
				.isExactlyInstanceOf(UserException.class);
	}

	@Test
	void validateWhenUsernameMoreThanMax() {
		assertThatThrownBy(() -> userValidator.validateUsername("111111111111"))
				.isExactlyInstanceOf(UserException.class);
	}

	@Test
	void validateWhenPasswordIsNull() {
		assertThatThrownBy(() -> userValidator.validatePassword(null))
				.isExactlyInstanceOf(UserException.class);
	}

	@Test
	void validateWhenPasswordIsBlank() {
		assertThatThrownBy(() -> userValidator.validatePassword(" "))
				.isExactlyInstanceOf(UserException.class);
	}

	@Test
	void validateWhenPasswordLessThanMin() {
		assertThatThrownBy(() -> userValidator.validatePassword("111"))
				.isExactlyInstanceOf(UserException.class);
	}

	@Test
	void validateWhenPasswordMoreThanMax() {
		assertThatThrownBy(() -> userValidator.validatePassword("111111111111"))
				.isExactlyInstanceOf(UserException.class);
	}

}
