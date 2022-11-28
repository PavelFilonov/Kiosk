package com.example.kiosk.service;

import com.example.kiosk.entity.User;
import com.example.kiosk.exception.UserException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	User save(User user) throws UserException;

}
