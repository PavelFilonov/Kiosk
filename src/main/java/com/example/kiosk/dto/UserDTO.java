package com.example.kiosk.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

	private Long id;

	private String username;

	private String password;

	private List<SubjectDTO> subjects;

}
