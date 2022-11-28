package com.example.kiosk.service;

import static org.mockito.Mockito.lenient;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.entity.TypeSubject;
import com.example.kiosk.entity.User;
import com.example.kiosk.repository.SubjectRepository;
import com.example.kiosk.repository.UserRepository;
import com.example.kiosk.validator.BookValidator;
import com.example.kiosk.validator.JournalValidator;
import com.example.kiosk.validator.NewspaperValidator;
import com.example.kiosk.validator.SubjectValidator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {

	@Mock
	protected SubjectRepository subjectRepository;

	@Mock
	protected UserRepository userRepository;

	@Mock
	protected SubjectValidator subjectValidator;

	@Mock
	protected BookValidator bookValidator;

	@Mock
	protected NewspaperValidator newspaperValidator;

	@Mock
	protected JournalValidator journalValidator;

	protected Subject book;

	protected Subject newspaper;

	protected Subject journal;

	protected User user;

	protected List<Subject> subjects;

	protected void setUpParent() {
		user = User.builder()
				.id(1L)
				.username("Alisa")
				.password("12345")
				.build();

		book = Subject.builder()
				.id(1L)
				.amount(3)
				.author("Cherenkova")
				.publisher("VSU")
				.title("Book")
				.typeSubject(TypeSubject.BOOK)
				.numberPage(43)
				.user(user)
				.build();

		journal = Subject.builder()
				.id(2L)
				.amount(3)
				.number(2)
				.title("Journal")
				.typeSubject(TypeSubject.JOURNAL)
				.dateRelease(LocalDate.of(2010, 10, 10))
				.numberPage(43)
				.user(user)
				.build();

		newspaper = Subject.builder()
				.id(3L)
				.amount(3)
				.number(3)
				.title("Newspaper")
				.typeSubject(TypeSubject.NEWSPAPER)
				.dateRelease(LocalDate.of(2010, 10, 10))
				.user(user)
				.build();

		subjects = new ArrayList<>();
		subjects.add(book);
		subjects.add(journal);
		subjects.add(newspaper);

		lenient().when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
		lenient().when(userRepository.findByUsername(null)).thenReturn(null);
		lenient().when(subjectRepository.findAllByUser(user)).thenReturn(subjects);
		lenient().when(subjectRepository.findAllByUser(null)).thenReturn(Collections.emptyList());
		lenient().when(subjectRepository.findByUserAndTypeSubject(user, TypeSubject.BOOK))
				.thenReturn(Collections.singletonList(book));
		lenient().when(subjectRepository.findByUserAndTypeSubject(user, TypeSubject.JOURNAL))
				.thenReturn(Collections.singletonList(journal));
		lenient().when(subjectRepository.findByUserAndTypeSubject(user, TypeSubject.NEWSPAPER))
				.thenReturn(Collections.singletonList(newspaper));

	}

}
