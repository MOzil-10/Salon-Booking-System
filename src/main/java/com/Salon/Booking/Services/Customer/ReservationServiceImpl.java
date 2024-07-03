package com.Salon.Booking.Services.Customer;

import com.Salon.Booking.DTO.ReservationDto;
import com.Salon.Booking.Entity.Product;
import com.Salon.Booking.Entity.Reservation;
import com.Salon.Booking.Entity.User;
import com.Salon.Booking.Exception.ResourceNotFoundException;
import com.Salon.Booking.Repository.ProductRepository;
import com.Salon.Booking.Repository.ReservationRepository;
import com.Salon.Booking.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    @Override
    public ReservationDto createReservation(Long userId, Long productId, ReservationDto reservationDto) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setProduct(product);
        reservation.setAdmin(product.getUser()); // Assuming the admin is the user who added the product
        reservation.setReservationStatus(reservationDto.getReservationStatus());
        reservation.setReviewStatus(reservationDto.getReviewStatus());
        reservation.setBookDate(new Date());

        reservation = reservationRepository.save(reservation);
        return reservation.toDto();
    }
}
