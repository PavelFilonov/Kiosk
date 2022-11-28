package com.example.kiosk.mapper;

import com.example.kiosk.dto.NewspaperDTO;
import com.example.kiosk.entity.Subject;

public class NewspaperMapper {

	public static NewspaperDTO map(Subject subject) {
		return NewspaperDTO.builder()
				.id(subject.getId())
				.amount(subject.getAmount())
				.title(subject.getTitle())
				.dateRelease(subject.getDateRelease())
				.number(subject.getNumber())
				.user(subject.getUser())
				.build();
	}

	public static Subject map(NewspaperDTO newspaperDTO) {
		return Subject.builder()
				.id(newspaperDTO.getId())
				.amount(newspaperDTO.getAmount())
				.title(newspaperDTO.getTitle())
				.dateRelease(newspaperDTO.getDateRelease())
				.number(newspaperDTO.getNumber())
				.user(newspaperDTO.getUser())
				.build();
	}

}
