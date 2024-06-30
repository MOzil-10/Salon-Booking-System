package com.Salon.Booking.DTO;

import com.Salon.Booking.Enum.UserRole;
import lombok.Data;

@Data
public class SignUpRequestDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
}
