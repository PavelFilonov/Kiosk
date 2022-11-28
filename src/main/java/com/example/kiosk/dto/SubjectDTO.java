package com.example.kiosk.dto;

import com.example.kiosk.entity.TypeSubject;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDTO {

	private Long id;

	private String title;

	private int amount;

	private String author;

	private String publisher;

	private int numberPage;

	private int number;

	private LocalDate dateRelease;

	private TypeSubject typeSubject;

}
