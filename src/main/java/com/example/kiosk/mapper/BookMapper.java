package com.example.kiosk.mapper;

import com.example.kiosk.dto.BookDTO;
import com.example.kiosk.entity.Subject;

public class BookMapper {

	public static BookDTO map(Subject subject) {
		return BookDTO.builder()
				.id(subject.getId())
				.user(subject.getUser())
				.title(subject.getTitle())
				.amount(subject.getAmount())
				.author(subject.getAuthor())
				.publisher(subject.getPublisher())
				.numberPage(subject.getNumberPage())
				.build();
	}

	public static Subject map(BookDTO bookDTO) {
		return Subject.builder()
				.id(bookDTO.getId())
				.user(bookDTO.getUser())
				.title(bookDTO.getTitle())
				.amount(bookDTO.getAmount())
				.author(bookDTO.getAuthor())
				.publisher(bookDTO.getPublisher())
				.numberPage(bookDTO.getNumberPage())
				.build();
	}
}
