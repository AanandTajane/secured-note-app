package com.secure.notes.services.Impl;

import com.secure.notes.dto.UserDto;
import com.secure.notes.models.AppRole;
import com.secure.notes.models.Role;
import com.secure.notes.models.User;
import com.secure.notes.repositories.UserRepository;
import com.secure.notes.repositories.RoleRepository;
import com.secure.notes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<User>  getAllUsers() {
        return userRepository.findAll();


    }

    @Override
    public void updateUserRole(Long userId, String roleName) {

        // Step 1: Retrieve the User by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Step 2: Validate and Retrieve the Role by Role Name
        AppRole appRole = AppRole.valueOf(roleName); // Convert string role name to enum
        Role role = roleRepository.findByRoleName(appRole)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Step 3: Update the User's Role
        user.setRole(role); // Set the new role

        // Step 4: Save the Updated User
        userRepository.save(user); // Persist changes to the database
    }

    @Override
    public UserDto getUserById(Long id) {

     User user =  userRepository.findById(id).orElseThrow();
       return convertToDto(user);

    }

    private UserDto convertToDto(User user) {
         return new UserDto(
                 user.getUserId(),
                 user.getUserName(),
                 user.getEmail(),
                 user.isAccountNonLocked(),
                 user.isAccountNonExpired(),
                 user.isCredentialsNonExpired(),
                 user.isEnabled(),
                 user.getCredentialsExpiryDate(),
                 user.getAccountExpiryDate(),
                 user.getTwoFactorSecret(),
                 user.isTwoFactorEnabled(),
                 user.getSignUpMethod(),
                 user.getRole(),
                 user.getCreatedDate(),
                 user.getUpdatedDate()

         );
    }
     @Override
        public User findByUsername(String username){
           Optional<User> user = userRepository.findByUserName(username);
           return user.orElseThrow( ()-> new RuntimeException("User Not found  with "+username));
        }
}
