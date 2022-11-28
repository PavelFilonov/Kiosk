package com.example.kiosk.mapper;

import com.example.kiosk.dto.JournalDTO;
import com.example.kiosk.entity.Subject;

public class JournalMapper {

	public static JournalDTO map(Subject subject) {
		return JournalDTO.builder()
				.id(subject.getId())
				.user(subject.getUser())
				.title(subject.getTitle())
				.amount(subject.getAmount())
				.number(subject.getNumber())
				.dateRelease(subject.getDateRelease())
				.numberPage(subject.getNumberPage())
				.build();
	}

	public static Subject map(JournalDTO journalDTO) {
		return Subject.builder()
				.id(journalDTO.getId())
				.user(journalDTO.getUser())
				.title(journalDTO.getTitle())
				.amount(journalDTO.getAmount())
				.number(journalDTO.getNumber())
				.dateRelease(journalDTO.getDateRelease())
				.numberPage(journalDTO.getNumberPage())
				.build();
	}

}
