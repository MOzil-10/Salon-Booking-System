package com.Salon.Booking.Entity;

import com.Salon.Booking.DTO.UserDto;
import com.Salon.Booking.Enum.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private UserRole role;

    public UserDto getUser() {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(email);
        userDto.setPhone(phone);
        userDto.setPassword(password);
        userDto.setRole(role);

        return userDto;
    }
}
