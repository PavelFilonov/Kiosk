package com.example.kiosk.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.entity.TypeSubject;
import com.example.kiosk.exception.BookException;
import com.example.kiosk.exception.SubjectException;
import com.example.kiosk.mapper.BookMapper;
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
class BookServiceTest extends SubjectServiceTest {

	private BookService bookService;

	private Subject newBook;

	@BeforeEach
	void setUp() {
		setUpParent();

		newBook = Subject.builder()
				.id(4L)
				.amount(5)
				.author("Author")
				.publisher("Publisher")
				.title("Title")
				.typeSubject(TypeSubject.BOOK)
				.numberPage(45)
				.user(user)
				.build();

		lenient().when(subjectRepository.findById(1L)).thenReturn(Optional.of(book));
		lenient().doAnswer((Answer<Void>) invocationOnMock -> {
			book = null;
			return null;
		}).when(subjectRepository).deleteById(1L);

		bookService = new BookService(subjectRepository, userRepository, subjectValidator, bookValidator);
	}

	@Test
	void findAll() {
		List<Subject> subjectList = bookService.findAll(user.getUsername());

		assertThat(subjectList).isEqualTo(subjects);
	}

	@Test
	void findAllWhenUserIsNull() {
		List<Subject> subjectList = bookService.findAll(null);

		assertThat(subjectList).isEmpty();
	}

	@Test
	void findAllCategory() {
		List<Subject> subjectList = bookService.findAllCategory(user.getUsername(), TypeSubject.BOOK);

		assertThat(subjectList).isEqualTo(Collections.singletonList(book));
	}

	@Test
	void getTypeSubject() {
		TypeSubject typeSubject = bookService.getTypeSubject();

		assertThat(typeSubject).isEqualTo(TypeSubject.BOOK);
	}

	@SneakyThrows
	@Test
	void save() {
		when(bookService.save(book, user.getUsername())).thenReturn(book);

		Subject savedBook = bookService.save(book, user.getUsername());

		assertThat(savedBook).isEqualTo(book);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectAuthor() {
		book.setAuthor("");
		doThrow(BookException.class).when(bookValidator).validateBook(book);

		assertThatThrownBy(() -> bookService.save(book, user.getUsername()))
				.isExactlyInstanceOf(BookException.class);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectPublisher() {
		book.setPublisher("");
		doThrow(BookException.class).when(bookValidator).validateBook(book);

		assertThatThrownBy(() -> bookService.save(book, user.getUsername()))
				.isExactlyInstanceOf(BookException.class);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectNumberPage() {
		book.setNumberPage(-1);
		doThrow(BookException.class).when(bookValidator).validateBook(book);

		assertThatThrownBy(() -> bookService.save(book, user.getUsername()))
				.isExactlyInstanceOf(BookException.class);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectTitle() {
		book.setTitle("");
		doThrow(SubjectException.class).when(subjectValidator).validateSubject(book);

		assertThatThrownBy(() -> bookService.save(book, user.getUsername()))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void saveWhenIncorrectAmount() {
		book.setAmount(-1);
		doThrow(SubjectException.class).when(subjectValidator).validateSubject(book);

		assertThatThrownBy(() -> bookService.save(book, user.getUsername()))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void update() {
		bookService.update(1L, newBook);

		assertThat(BookMapper.map(book)).isEqualTo(BookMapper.map(newBook));
	}

	@Test
	void updateWhenNotFoundBook() {
		assertThatThrownBy(() -> bookService.update(11L, newBook))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectAuthor() {
		newBook.setAuthor("");
		doThrow(BookException.class).when(bookValidator).validateBook(newBook);

		assertThatThrownBy(() -> bookService.update(1L, newBook))
				.isExactlyInstanceOf(BookException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectPublisher() {
		newBook.setPublisher("");
		doThrow(BookException.class).when(bookValidator).validateBook(newBook);

		assertThatThrownBy(() -> bookService.update(1L, newBook))
				.isExactlyInstanceOf(BookException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectNumberPage() {
		newBook.setNumberPage(-1);
		doThrow(BookException.class).when(bookValidator).validateBook(newBook);

		assertThatThrownBy(() -> bookService.update(1L, newBook))
				.isExactlyInstanceOf(BookException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectTitle() {
		newBook.setTitle("");
		doThrow(SubjectException.class).when(subjectValidator).validateSubject(newBook);

		assertThatThrownBy(() -> bookService.update(1L, newBook))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void updateWhenIncorrectAmount() {
		newBook.setAmount(-1);
		doThrow(SubjectException.class).when(subjectValidator).validateSubject(newBook);

		assertThatThrownBy(() -> bookService.update(1L, newBook))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void delete() {
		bookService.delete(1L);

		assertThat(book).isNull();
	}

	@Test
	void deleteWhenNotFound() {
		assertThatThrownBy(() -> bookService.delete(11L))
				.isExactlyInstanceOf(SubjectException.class);
	}

	@SneakyThrows
	@Test
	void findById() {
		Subject subject = bookService.findById(1L);

		assertThat(subject).isEqualTo(book);
	}

	@Test
	void notFoundById() {
		assertThatThrownBy(() -> bookService.findById(11L))
				.isExactlyInstanceOf(SubjectException.class);
	}

}