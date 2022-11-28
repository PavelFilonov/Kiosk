package com.example.kiosk.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;

import com.example.kiosk.dto.SubjectDTO;
import com.example.kiosk.dto.UserDTO;
import com.example.kiosk.entity.Subject;
import com.example.kiosk.entity.TypeSubject;
import com.example.kiosk.entity.User;
import java.time.LocalDate;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

	private UserMapper userMapper;

	@Mock
	private SubjectMapper subjectMapper;

	private User user;

	private UserDTO userDTO;

	private SubjectDTO subjectDTO;

	@BeforeEach
	void setUp() {
		Subject subject = Subject.builder()
				.id(1L)
				.title("title")
				.amount(1)
				.author("author")
				.publisher("publisher")
				.numberPage(2)
				.number(3)
				.dateRelease(LocalDate.now())
				.typeSubject(TypeSubject.BOOK)
				.build();

		subjectDTO = SubjectDTO.builder()
				.id(1L)
				.title("tit le")
				.amount(4)
				.author("aut hor")
				.publisher("pub lisher")
				.numberPage(5)
				.number(6)
				.dateRelease(LocalDate.now())
				.typeSubject(TypeSubject.JOURNAL)
				.build();

		user = User
				.builder()
				.id(1L)
				.username("User")
				.password("pass")
				.subjects(Collections.singletonList(subject))
				.build();

		userDTO = UserDTO.builder()
				.username("Name")
				.password("12345")
				.build();

		lenient().when(subjectMapper.mapListToDTO(Collections.singletonList(subject)))
				.thenReturn(Collections.singletonList(subjectDTO));

		userMapper = new UserMapper(subjectMapper);
	}

	@Test
	void mapToEntity() {
		User mappedUser = userMapper.map(userDTO);

		assertThat(mappedUser.getUsername()).isEqualTo(userDTO.getUsername());
		assertThat(mappedUser.getPassword()).isEqualTo(userDTO.getPassword());
	}

	@Test
	void mapToDTO() {
		UserDTO mappedUser = userMapper.map(user);

		assertThat(mappedUser.getId()).isEqualTo(user.getId());
		assertThat(mappedUser.getUsername()).isEqualTo(user.getUsername());
		assertThat(mappedUser.getPassword()).isEqualTo(user.getPassword());
		assertThat(mappedUser.getSubjects()).isEqualTo(Collections.singletonList(subjectDTO));
	}

}
