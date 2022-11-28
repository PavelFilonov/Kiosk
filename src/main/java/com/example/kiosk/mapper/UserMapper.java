package com.example.kiosk.mapper;

import com.example.kiosk.dto.UserDTO;
import com.example.kiosk.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

	private final SubjectMapper subjectMapper;

	public User map(UserDTO userDTO) {
		return User.builder()
				.username(userDTO.getUsername())
				.password(userDTO.getPassword())
				.build();
	}

	public UserDTO map(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.username(user.getUsername())
				.password(user.getPassword())
				.subjects(subjectMapper.mapListToDTO(user.getSubjects()))
				.build();
	}

}
