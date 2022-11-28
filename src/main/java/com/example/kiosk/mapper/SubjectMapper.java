package com.example.kiosk.mapper;

import com.example.kiosk.dto.SubjectDTO;
import com.example.kiosk.entity.Subject;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class SubjectMapper {

	public Subject map(SubjectDTO subjectDTO) {
		return Subject.builder()
				.title(subjectDTO.getTitle())
				.amount(subjectDTO.getAmount())
				.author(subjectDTO.getAuthor())
				.publisher(subjectDTO.getPublisher())
				.numberPage(subjectDTO.getNumberPage())
				.number(subjectDTO.getNumber())
				.dateRelease(subjectDTO.getDateRelease())
				.typeSubject(subjectDTO.getTypeSubject())
				.build();
	}

	public SubjectDTO map(Subject subject) {
		return SubjectDTO.builder()
				.id(subject.getId())
				.title(subject.getTitle())
				.amount(subject.getAmount())
				.author(subject.getAuthor())
				.publisher(subject.getPublisher())
				.numberPage(subject.getNumberPage())
				.number(subject.getNumber())
				.dateRelease(subject.getDateRelease())
				.typeSubject(subject.getTypeSubject())
				.build();
	}

	public List<SubjectDTO> mapListToDTO(List<Subject> subjects) {
		return subjects.stream().map(this::map).collect(Collectors.toList());
	}

}
