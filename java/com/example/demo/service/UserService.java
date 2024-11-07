package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

public interface UserService {
	
	User save (UserDto userDto);

    UserDetails loadUserByUsername(String name);
    boolean emailExists(String email);

}