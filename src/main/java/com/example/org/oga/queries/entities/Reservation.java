package com.example.org.oga.queries.entities;

import com.example.org.oga.commonapi.enums.ReservationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Reservation {
    @Id
    private String id;
    private String reference;
    private LocalDate dateReservation;
    private String lieuDep;
    private String lieuArr;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
