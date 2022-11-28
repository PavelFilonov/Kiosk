package com.example.kiosk.service;

import com.example.kiosk.entity.User;
import com.example.kiosk.exception.UserException;
import com.example.kiosk.repository.UserRepository;
import com.example.kiosk.validator.UserValidator;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final UserValidator userValidator;

	private final PasswordEncoder passwordEncoder;

	@Override
	public User save(User user) throws UserException {
		String username = user.getUsername();
		userValidator.validateUser(user);

		if (userRepository.existsByUsername(username)) {
			throw new UserException(String.format("User with name '%s' already exists", username));
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %s not found", username));
		}

		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				Collections.emptyList()
		);
	}

}
