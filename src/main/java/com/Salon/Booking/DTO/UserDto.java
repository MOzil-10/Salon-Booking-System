package com.Salon.Booking.DTO;

import com.Salon.Booking.Enum.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private UserRole role;
}
