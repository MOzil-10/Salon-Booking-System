package com.Salon.Booking.Response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String expiresIn;

    public LoginResponse(String token, String expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }
}
