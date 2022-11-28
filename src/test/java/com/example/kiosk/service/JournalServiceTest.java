package com.example.kiosk.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.entity.TypeSubject;
import com.example.kiosk.exception.JournalException;
import com.example.kiosk.exception.SubjectException;
import com.example.kiosk.mapper.JournalMapper;
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
class JournalServiceTest extends SubjectServiceTest {

	private JournalService journalService;

	private Subject newJournal;

	@BeforeEach
	void setUp() {
		setUpParent();

		newJournal = Subject.builder()
				.id(4L)
				.amount(5)
				.number(5)
				.title("Title")
				.typeSubject(TypeSubject.JOURNAL)
				.dateRelease(LocalDate.of(2010, 10, 10))
				.numberPage(45)
				.user(user)
				.build();

		lenient().when(subjectRepository.findById(2L)).thenReturn(Optional.of(journal));
		lenient().doAnswer((Answer<Void>) invocationOnMock -> {
			journal = null;
			return null;
		}).when(subjectRepository).deleteById(2L);

		journalService = new JournalService(subjectRepository, userRepository, subjectValidator, journalValidator);
	}

	@Test
	void findAll() {
		List<Subject> subjectList = journalService.findAll(user.getUsername());

		assertThat(subjectList).isEqualTo(subjects);
	}

	@Test
	void findAllWhenUserIsNull() {
		List<Subject> subjectList = journalService.findAll(null);

		assertThat(subjectList).isEmpty();
	}

	@Test
	void findAllCategory() {
		List<Subject> subjectList = journalService.findAllCategory(user.getUsername(), TypeSubject.JOURNAL);

		assertThat(subjectList).isEqualTo(Collections.singletonList(journal));
	}

	@Test
	void getTypeSubject() {
		TypeSubject typeSubject = journalService.getTypeSubject();

		assertThat(typeSubject).isEqualTo(TypeSubject.JOURNAL);
	}

	@SneakyThrows
	@Test
	void save() {
		when(journalService.save(journal, user.getUsername())).thenReturn(journal);

		Subject savedJournal = journalService.save(journal, user.getUsername());

		assertThat(savedJournal).isEqualTo(journal);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectNumber() {
		journal.setNumber(-1);
		doThrow(JournalException.class).when(journalValidator).validateJournal(journal);

		assertThatThrownBy(() -> journalService.save(journal, user.getUsername()))
				.isExactlyInstanceOf(JournalException.class);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectDateRelease() {
		journal.setDateRelease(LocalDate.MAX);
		doThrow(JournalException.class).when(journalValidator).validateJournal(journal);

		assertThatThrownBy(() -> journalService.save(journal, user.getUsername()))
				.isExactlyInstanceOf(JournalException.class);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectNumberPage() {
		journal.setNumberPage(-1);
		doThrow(JournalException.class).when(journalValidator).validateJournal(journal);

		assertThatThrownBy(() -> journalService.save(journal, user.getUsername()))
				.isExactlyInstanceOf(JournalException.class);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectTitle() {
		journal.setTitle("");
		doThrow(SubjectException.class).when(subjectValidator).validateSubject(journal);

		assertThatThrownBy(() -> journalService.save(journal, user.getUsername()))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectAmount() {
		journal.setAmount(-1);
		doThrow(SubjectException.class).when(subjectValidator).validateSubject(journal);

		assertThatThrownBy(() -> journalService.save(journal, user.getUsername()))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void update() {
		journalService.update(2L, newJournal);

		assertThat(JournalMapper.map(journal)).isEqualTo(JournalMapper.map(newJournal));
	}

	@Test
	void updateWhenNotFoundBook() {
		assertThatThrownBy(() -> journalService.update(22L, newJournal))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectNumber() {
		newJournal.setNumber(-1);
		doThrow(JournalException.class).when(journalValidator).validateJournal(newJournal);

		assertThatThrownBy(() -> journalService.update(1L, newJournal))
				.isExactlyInstanceOf(JournalException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectDateRelease() {
		newJournal.setDateRelease(LocalDate.MAX);
		doThrow(JournalException.class).when(journalValidator).validateJournal(newJournal);

		assertThatThrownBy(() -> journalService.update(1L, newJournal))
				.isExactlyInstanceOf(JournalException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectNumberPage() {
		newJournal.setNumberPage(-1);
		doThrow(JournalException.class).when(journalValidator).validateJournal(newJournal);

		assertThatThrownBy(() -> journalService.update(1L, newJournal))
				.isExactlyInstanceOf(JournalException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectTitle() {
		newJournal.setTitle("");
		doThrow(SubjectException.class).when(subjectValidator).validateSubject(newJournal);

		assertThatThrownBy(() -> journalService.update(1L, newJournal))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectAmount() {
		newJournal.setAmount(-1);
		doThrow(SubjectException.class).when(subjectValidator).validateSubject(newJournal);

		assertThatThrownBy(() -> journalService.update(1L, newJournal))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void delete() {
		journalService.delete(2L);

		assertThat(journal).isNull();
	}

	@Test
	void deleteWhenNotFound() {
		assertThatThrownBy(() -> journalService.delete(22L))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void findById() {
		Subject subject = journalService.findById(2L);

		assertThat(subject).isEqualTo(journal);
	}

	@Test
	void notFoundById() {
		assertThatThrownBy(() -> journalService.findById(22L))
				.isExactlyInstanceOf(SubjectException.class);
	}

}