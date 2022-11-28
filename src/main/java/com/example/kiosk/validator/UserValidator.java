package com.example.kiosk.validator;

import com.example.kiosk.entity.User;
import com.example.kiosk.exception.UserException;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {

	public void validateUser(User user) throws UserException {
		validateUsername(user.getUsername());
		validatePassword(user.getPassword());
	}

	public void validateUsername(String username) throws UserException {
		if (Objects.isNull(username) || username.isBlank()) {
			throw new UserException("Blank username");
		}
		if (username.length() < 4 || username.length() > 10) {
			throw new UserException("Username should be from 5 to 15 symbols");
		}
	}

	public void validatePassword(String password) throws UserException {
		if (Objects.isNull(password) || password.isBlank()) {
			throw new UserException("Blank password");
		}
		if (password.length() < 4 || password.length() > 10) {
			throw new UserException("Password should be from 5 to 15 symbols");
		}
	}

}
