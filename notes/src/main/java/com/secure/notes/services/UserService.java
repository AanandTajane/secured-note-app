package com.secure.notes.services;

import com.secure.notes.dto.UserDto;
import com.secure.notes.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {


         List<User> getAllUsers();

          void updateUserRole(Long userId,String roleName);


          public UserDto getUserById(Long id);

          User findByUsername(String username);
}
