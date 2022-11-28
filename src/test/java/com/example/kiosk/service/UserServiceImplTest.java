package com.example.kiosk.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;

import com.example.kiosk.entity.User;
import com.example.kiosk.exception.UserException;
import com.example.kiosk.repository.UserRepository;
import com.example.kiosk.validator.UserValidator;
import java.util.Optional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserValidator userValidator;

	@Mock
	private PasswordEncoder passwordEncoder;

	private User user;

	@SneakyThrows
	@BeforeEach
	void setUp() {
		user = User.builder()
				.id(1L)
				.username("Alisa")
				.password("12345")
				.build();

		lenient().when(userRepository.existsByUsername("Alisa")).thenReturn(true);
		lenient().when(userRepository.save(user)).thenReturn(user);
		lenient().when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		lenient().when(userRepository.findByUsername("Alisa")).thenReturn(user);
		lenient().when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());

		lenient().doThrow(UserException.class).when(userValidator).validateUsername(null);
		lenient().doThrow(UserException.class).when(userValidator).validatePassword(null);

		userService = new UserServiceImpl(userRepository, userValidator, passwordEncoder);
	}

	@SneakyThrows
	@Test
	void save() {
		user.setUsername("Name");
		User newUser = User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.build();

		User savedUser = userService.save(newUser);

		assertThat(savedUser.getUsername()).isEqualTo(newUser.getUsername());
		assertThat(savedUser.getPassword()).isEqualTo(newUser.getPassword());
	}

	@Test
	void saveWhenUsernameExists() {
		assertThatThrownBy(() -> userService.save(user))
				.isExactlyInstanceOf(UserException.class);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectUsername() {
		User incorrectUsernameUser = User.builder()
				.username(null)
				.password("12345")
				.build();
		doThrow(UserException.class).when(userValidator).validateUser(incorrectUsernameUser);

		assertThatThrownBy(() -> userService.save(incorrectUsernameUser))
				.isExactlyInstanceOf(UserException.class);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectPassword() {
		User incorrectPasswordUser = User.builder()
				.username("name")
				.password(null)
				.build();
		doThrow(UserException.class).when(userValidator).validateUser(incorrectPasswordUser);

		assertThatThrownBy(() -> userService.save(incorrectPasswordUser))
				.isExactlyInstanceOf(UserException.class);
	}

	@Test
	void loadUserByUsername() {
		user.setUsername("Name");

		UserDetails userDetails = userService.loadUserByUsername("Alisa");

		assertThat(userDetails.getUsername()).isEqualTo(user.getUsername());
		assertThat(userDetails.getPassword()).isEqualTo(user.getPassword());
	}

	@Test
	void notFoundLoadUserByUsername() {
		assertThatThrownBy(() -> userService.loadUserByUsername("user"))
				.isExactlyInstanceOf(UsernameNotFoundException.class);
	}

}