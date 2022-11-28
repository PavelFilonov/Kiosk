package com.example.kiosk.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.entity.TypeSubject;
import com.example.kiosk.exception.NewspaperException;
import com.example.kiosk.exception.SubjectException;
import com.example.kiosk.mapper.NewspaperMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
class NewspaperServiceTest extends SubjectServiceTest {

	private NewspaperService newspaperService;

	private Subject newNewspaper;

	@BeforeEach
	void setUp() {
		setUpParent();

		newNewspaper = Subject.builder()
				.id(4L)
				.amount(5)
				.number(5)
				.title("Title")
				.typeSubject(TypeSubject.NEWSPAPER)
				.dateRelease(LocalDate.of(2010, 10, 10))
				.user(user)
				.build();

		lenient().when(subjectRepository.findById(3L)).thenReturn(Optional.of(newspaper));
		lenient().doAnswer((Answer<Void>) invocationOnMock -> {
			newspaper = null;
			return null;
		}).when(subjectRepository).deleteById(3L);

		newspaperService = new NewspaperService(subjectRepository, userRepository, subjectValidator, newspaperValidator);
	}

	@Test
	void findAll() {
		List<Subject> subjectList = newspaperService.findAll(user.getUsername());

		assertThat(subjectList).isEqualTo(subjects);
	}

	@Test
	void findAllWhenUserIsNull() {
		List<Subject> subjectList = newspaperService.findAll(null);

		assertThat(subjectList).isEmpty();
	}

	@Test
	void findAllCategory() {
		List<Subject> subjectList = newspaperService.findAllCategory(user.getUsername(), TypeSubject.NEWSPAPER);

		assertThat(subjectList).isEqualTo(Collections.singletonList(newspaper));
	}

	@Test
	void getTypeSubject() {
		TypeSubject typeSubject = newspaperService.getTypeSubject();

		assertThat(typeSubject).isEqualTo(TypeSubject.NEWSPAPER);
	}

	@SneakyThrows
	@Test
	void save() {
		when(newspaperService.save(newspaper, user.getUsername())).thenReturn(newspaper);

		Subject savedNewspaper = newspaperService.save(newspaper, user.getUsername());

		assertThat(savedNewspaper).isEqualTo(newspaper);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectNumber() {
		newspaper.setNumber(-1);
		doThrow(NewspaperException.class).when(newspaperValidator).validateNewspaper(newspaper);

		assertThatThrownBy(() -> newspaperService.save(newspaper, user.getUsername()))
				.isExactlyInstanceOf(NewspaperException.class);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectDateRelease() {
		newspaper.setDateRelease(LocalDate.MAX);
		doThrow(NewspaperException.class).when(newspaperValidator).validateNewspaper(newspaper);

		assertThatThrownBy(() -> newspaperService.save(newspaper, user.getUsername()))
				.isExactlyInstanceOf(NewspaperException.class);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectTitle() {
		newspaper.setTitle("");
		doThrow(SubjectException.class).when(subjectValidator).validateSubject(newspaper);

		assertThatThrownBy(() -> newspaperService.save(newspaper, user.getUsername()))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectAmount() {
		newspaper.setAmount(-1);
		doThrow(SubjectException.class).when(subjectValidator).validateSubject(newspaper);

		assertThatThrownBy(() -> newspaperService.save(newspaper, user.getUsername()))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void update() {
		newspaperService.update(3L, newNewspaper);

		assertThat(NewspaperMapper.map(newspaper)).isEqualTo(NewspaperMapper.map(newNewspaper));
	}

	@Test
	void updateWhenNotFoundBook() {
		assertThatThrownBy(() -> newspaperService.update(33L, newNewspaper))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectNumber() {
		newNewspaper.setNumber(-1);
		doThrow(NewspaperException.class).when(newspaperValidator).validateNewspaper(newNewspaper);

		assertThatThrownBy(() -> newspaperService.update(1L, newNewspaper))
				.isExactlyInstanceOf(NewspaperException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectDateRelease() {
		newNewspaper.setDateRelease(LocalDate.MAX);
		doThrow(NewspaperException.class).when(newspaperValidator).validateNewspaper(newNewspaper);

		assertThatThrownBy(() -> newspaperService.update(1L, newNewspaper))
				.isExactlyInstanceOf(NewspaperException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectTitle() {
		newNewspaper.setTitle("");
		doThrow(SubjectException.class).when(subjectValidator).validateSubject(newNewspaper);

		assertThatThrownBy(() -> newspaperService.update(1L, newNewspaper))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectAmount() {
		newNewspaper.setAmount(-1);
		doThrow(SubjectException.class).when(subjectValidator).validateSubject(newNewspaper);

		assertThatThrownBy(() -> newspaperService.update(1L, newNewspaper))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void delete() {
		newspaperService.delete(3L);

		assertThat(newspaper).isNull();
	}

	@Test
	void deleteWhenNotFound() {
		assertThatThrownBy(() -> newspaperService.delete(33L))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void findById() {
		Subject subject = newspaperService.findById(3L);

		assertThat(subject).isEqualTo(newspaper);
	}

	@Test
	void notFoundById() {
		assertThatThrownBy(() -> newspaperService.findById(33L))
				.isExactlyInstanceOf(SubjectException.class);
	}

}