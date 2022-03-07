package com.devoteam.demoassignments.recipemanager.controller;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.devoteam.demoassignments.recipemanager.jwt.CustomUserDetails;
import com.devoteam.demoassignments.recipemanager.jwt.JwtHelper;
import com.devoteam.demoassignments.recipemanager.repository.RoleRepository;
import com.devoteam.demoassignments.recipemanager.repository.UserRepository;
import com.devoteam.demoassignments.recipemanager.entity.Role;
import com.devoteam.demoassignments.recipemanager.entity.User;
import com.devoteam.demoassignments.recipemanager.enums.RoleType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.devoteam.demoassignments.recipemanager.controller.dto.LoginDTO;
import com.devoteam.demoassignments.recipemanager.controller.dto.SuccessResponseDTO;
import com.devoteam.demoassignments.recipemanager.controller.dto.JwtTokenDTO;
import com.devoteam.demoassignments.recipemanager.controller.dto.AddUserDTO;
import com.devoteam.demoassignments.recipemanager.exception.dto.ResponseDTO;

// For easier develop and testing
// On production this should be more restict
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(RootConstants.ROOT_AUTHENTICATION)
public class AuthenticationController {
  private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
  private static final String ROLE_NOT_EXIST = "Error: Role does not exist";

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtHelper jwtHelper;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  AuthenticationManager authenticationManager;

  @PostMapping("/login")
  public ResponseEntity<SuccessResponseDTO> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
    logger.info("Authenticate user: {} ", loginDTO.getUsername());

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtHelper.generateJwtToken(authentication);

    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    JwtTokenDTO jwtTokenDTO = new JwtTokenDTO(jwt,userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    return ResponseEntity.ok().body(new SuccessResponseDTO("User authenticated usccesfully", jwtTokenDTO));
  }

  @PostMapping("/register")
  public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody AddUserDTO addUserDTO) {
    logger.info("Register new user: {}", addUserDTO.getUsername());

    if (Boolean.TRUE.equals(userRepository.existsByUsername(addUserDTO.getUsername()))) {
      return ResponseEntity.badRequest().body(new ResponseDTO("Username already taken!"));
    }

    if (Boolean.TRUE.equals(userRepository.existsByEmail(addUserDTO.getEmail()))) {
      return ResponseEntity.badRequest().body(new ResponseDTO("Email already taken!"));
    }

    User user = new User(addUserDTO.getUsername(), addUserDTO.getEmail(), encoder.encode(addUserDTO.getPassword()));

    Set<String> stringRoles = addUserDTO.getRole();
    Set<Role> roles = new HashSet<>();

    if (stringRoles == null) {
      Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ROLE_NOT_EXIST));
      roles.add(userRole);
    } else {
      stringRoles.forEach(role -> {
        switch (role.toLowerCase()) {
          case "admin":
            Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ROLE_NOT_EXIST));
            roles.add(adminRole);
            break;
          default:
            Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ROLE_NOT_EXIST));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new SuccessResponseDTO("User registered successfully!"));
  }

}