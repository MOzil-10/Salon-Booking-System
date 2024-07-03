package com.Salon.Booking.DTO;

import com.Salon.Booking.Enum.ReservationStatus;
import com.Salon.Booking.Enum.ReviewStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto {
    private Long id;
    private ReservationStatus reservationStatus;
    private ReviewStatus reviewStatus;
    private Date bookDate;
    private Long adminId;
    private Long productId;
    private Long userId;
    private String product;
}
