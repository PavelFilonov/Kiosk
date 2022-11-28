package com.example.kiosk.dto;

import com.example.kiosk.entity.User;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NewspaperDTO {

	private Long id;

	@EqualsAndHashCode.Include()
	private User user;

	@EqualsAndHashCode.Include()
	private String title;

	@EqualsAndHashCode.Include()
	private int amount;

	@EqualsAndHashCode.Include()
	private int number;

	@EqualsAndHashCode.Include()
	private LocalDate dateRelease;

}
