package com.Salon.Booking.Services.Customer;

import com.Salon.Booking.DTO.ReservationDto;
import com.Salon.Booking.Exception.ResourceNotFoundException;

public interface ReservationService {

    ReservationDto createReservation(Long userId, Long productId, ReservationDto reservationDto) throws ResourceNotFoundException;
}
