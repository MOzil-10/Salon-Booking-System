package com.Salon.Booking.Controller;

import com.Salon.Booking.DTO.LoginUserDto;
import com.Salon.Booking.DTO.SignUpRequestDto;
import com.Salon.Booking.DTO.UserDto;
import com.Salon.Booking.Entity.User;
import com.Salon.Booking.Enum.UserRole;
import com.Salon.Booking.Response.LoginResponse;
import com.Salon.Booking.Services.Auth.AuthenticationService;
import com.Salon.Booking.Utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/salon")
public class AuthController {

    private final AuthenticationService authService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/customer")
    public ResponseEntity<?> signupUser(@RequestBody SignUpRequestDto signUpRequestDto) {
        if(authService.findUserByEmail(signUpRequestDto.getEmail())) {
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        }

        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        signUpRequestDto.setPassword(encodedPassword);

        UserDto createdUser = authService.registerUser(signUpRequestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/admin")
    public ResponseEntity<?> signupAdmin(@RequestBody SignUpRequestDto signUpRequestDto) {
        if(authService.findUserByEmail(signUpRequestDto.getEmail())) {
            return new ResponseEntity<>("Admin already exists", HttpStatus.NOT_ACCEPTABLE);
        }

        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        signUpRequestDto.setPassword(encodedPassword);
        signUpRequestDto.setRole(UserRole.ADMIN);

        UserDto createdUser = authService.registerUser(signUpRequestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authService.authenticate(loginUserDto);
        String jwtToken = jwtUtil.generateToken(authenticatedUser.getEmail());
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtUtil.getExpirationTime().toString());
        return ResponseEntity.ok(loginResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
