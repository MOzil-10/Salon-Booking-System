package com.Salon.Booking.Controller;

import com.Salon.Booking.DTO.ReservationDto;
import com.Salon.Booking.Exception.ResourceNotFoundException;
import com.Salon.Booking.Services.Customer.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<ReservationDto> createReservation(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestBody ReservationDto reservationDto) {
        try {
            ReservationDto createdReservation = reservationService.createReservation(userId, productId, reservationDto);
            return ResponseEntity.ok(createdReservation);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
