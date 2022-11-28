package com.example.kiosk.dto;

import com.example.kiosk.entity.User;
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
public class BookDTO {

	private Long id;

	@EqualsAndHashCode.Include()
	private User user;

	@EqualsAndHashCode.Include()
	private String title;

	@EqualsAndHashCode.Include()
	private int amount;

	@EqualsAndHashCode.Include()
	private String author;

	@EqualsAndHashCode.Include()
	private String publisher;

	@EqualsAndHashCode.Include()
	private int numberPage;

}
