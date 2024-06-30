package com.Salon.Booking.Services.Auth;

import com.Salon.Booking.DTO.LoginUserDto;
import com.Salon.Booking.DTO.SignUpRequestDto;
import com.Salon.Booking.DTO.UserDto;
import com.Salon.Booking.Entity.User;
import com.Salon.Booking.Enum.UserRole;
import com.Salon.Booking.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDto registerUser(SignUpRequestDto signUpRequestDto) {
        User user = createUserFromSignUpRequest(signUpRequestDto, UserRole.CUSTOMER);
        return userRepository.save(user).getUser();
    }

    @Override
    public UserDto registerAdmin(SignUpRequestDto signUpRequestDto) {
        User user = createUserFromSignUpRequest(signUpRequestDto, UserRole.ADMIN);
        return userRepository.save(user).getUser();
    }

    private User createUserFromSignUpRequest(SignUpRequestDto signUpRequestDto, UserRole role) {
        User user = new User();
        user.setFirstName(signUpRequestDto.getFirstName());
        user.setLastName(signUpRequestDto.getLastName());
        user.setEmail(signUpRequestDto.getEmail());
        user.setPhone(signUpRequestDto.getPhone());
        user.setPassword(signUpRequestDto.getPassword());
        user.setRole(role);
        return user;
    }

    @Override
    public boolean findUserByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User authenticate(LoginUserDto input) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Authentication failed: " + e.getMessage());
        }

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
