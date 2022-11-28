package com.example.kiosk.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.kiosk.dto.SubjectDTO;
import com.example.kiosk.entity.Subject;
import com.example.kiosk.entity.TypeSubject;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SubjectMapperTest {

	private SubjectMapper subjectMapper;

	private Subject subject;

	private SubjectDTO subjectDTO;

	@BeforeEach
	void setUp() {
		subject = Subject.builder()
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
				.title("tit le")
				.amount(4)
				.author("aut hor")
				.publisher("pub lisher")
				.numberPage(5)
				.number(6)
				.dateRelease(LocalDate.now())
				.typeSubject(TypeSubject.JOURNAL)
				.build();

		subjectMapper = new SubjectMapper();
	}

	@Test
	void mapToEntity() {
		Subject mappedSubject = subjectMapper.map(subjectDTO);

		assertThat(mappedSubject.getTitle()).isEqualTo(subjectDTO.getTitle());
		assertThat(mappedSubject.getAmount()).isEqualTo(subjectDTO.getAmount());
		assertThat(mappedSubject.getAuthor()).isEqualTo(subjectDTO.getAuthor());
		assertThat(mappedSubject.getPublisher()).isEqualTo(subjectDTO.getPublisher());
		assertThat(mappedSubject.getNumberPage()).isEqualTo(subjectDTO.getNumberPage());
		assertThat(mappedSubject.getNumber()).isEqualTo(subjectDTO.getNumber());
		assertThat(mappedSubject.getDateRelease()).isEqualTo(subjectDTO.getDateRelease());
		assertThat(mappedSubject.getTypeSubject()).isEqualTo(subjectDTO.getTypeSubject());
	}

	@Test
	void mapToDTO() {
		SubjectDTO mappedSubject = subjectMapper.map(subject);

		assertThat(mappedSubject.getId()).isEqualTo(subject.getId());
		assertThat(mappedSubject.getTitle()).isEqualTo(subject.getTitle());
		assertThat(mappedSubject.getAmount()).isEqualTo(subject.getAmount());
		assertThat(mappedSubject.getAuthor()).isEqualTo(subject.getAuthor());
		assertThat(mappedSubject.getPublisher()).isEqualTo(subject.getPublisher());
		assertThat(mappedSubject.getNumberPage()).isEqualTo(subject.getNumberPage());
		assertThat(mappedSubject.getNumber()).isEqualTo(subject.getNumber());
		assertThat(mappedSubject.getDateRelease()).isEqualTo(subject.getDateRelease());
		assertThat(mappedSubject.getTypeSubject()).isEqualTo(subject.getTypeSubject());
	}

}
