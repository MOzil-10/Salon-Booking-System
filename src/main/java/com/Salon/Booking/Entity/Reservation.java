package com.Salon.Booking.Entity;

import com.Salon.Booking.DTO.ReservationDto;
import com.Salon.Booking.Enum.ReservationStatus;
import com.Salon.Booking.Enum.ReviewStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    private Date bookDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    public ReservationDto toDto() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(id);
        reservationDto.setReservationStatus(reservationStatus);
        reservationDto.setReviewStatus(reviewStatus);
        reservationDto.setBookDate(bookDate);
        reservationDto.setAdminId(admin.getId());
        reservationDto.setProductId(product.getId());
        reservationDto.setUserId(user.getId());
        reservationDto.setProduct(product.getName());
        return reservationDto;
    }
}
