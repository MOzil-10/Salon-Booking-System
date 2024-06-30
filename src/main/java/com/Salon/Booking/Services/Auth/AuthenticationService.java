package com.Salon.Booking.Services.Auth;

import com.Salon.Booking.DTO.LoginUserDto;
import com.Salon.Booking.DTO.SignUpRequestDto;
import com.Salon.Booking.DTO.UserDto;
import com.Salon.Booking.Entity.User;

public interface AuthenticationService {
    UserDto registerUser(SignUpRequestDto signUpRequestDto);
    boolean findUserByEmail(String email);
    UserDto registerAdmin(SignUpRequestDto signUpRequestDto);
    User authenticate(LoginUserDto input);
}
