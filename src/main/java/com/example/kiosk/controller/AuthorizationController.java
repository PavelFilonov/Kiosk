package com.example.kiosk.controller;

import com.example.kiosk.dto.UserDTO;
import com.example.kiosk.exception.UserException;
import com.example.kiosk.mapper.UserMapper;
import com.example.kiosk.service.UserService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthorizationController {

	private final UserService userService;

	private final UserMapper userMapper;

	@GetMapping("/registration")
	public String registration(@ModelAttribute("user") UserDTO user) {
		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("user") UserDTO user, Map<String, Object> model) {
		try {
			userService.save(userMapper.map(user));
		} catch (UserException e) {
			model.put("error", e.getMessage());
			return "registration";
		}
		return "redirect:/subjects";
	}

}
