package com.example.org.oga.commonapi.events;

import com.example.org.oga.commonapi.enums.ReservationStatus;
import lombok.Getter;

import java.time.LocalDate;

public class ReservationCreatedEvent extends BaseEvent<String>{
    @Getter
    private String reference;
    @Getter
    private LocalDate dateReservation;

    @Getter private String lieuDep;
    @Getter private String lieuArr;
    @Getter private ReservationStatus status;

    public ReservationCreatedEvent(String id, String reference  ,LocalDate dateReservation, String lieuDep, String lieuArr, ReservationStatus status) {
        super(id);
        this.reference = reference;
        this.dateReservation = dateReservation;
        this.lieuDep = lieuDep;
        this.lieuArr = lieuArr;
        this.status = status;
    }
}
