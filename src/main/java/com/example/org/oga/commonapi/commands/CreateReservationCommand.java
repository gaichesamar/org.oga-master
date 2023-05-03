package com.example.org.oga.commonapi.commands;

import com.example.org.oga.commonapi.enums.ReservationStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;


public class CreateReservationCommand extends BaseCommand<String>{
    @Getter
    private String reference;
    @Getter
    private LocalDate dateReservation;

    @Getter private String lieuDep;
    @Getter private String lieuArr;
    private  ReservationStatus reservationStatus;

    public CreateReservationCommand(String id, String reference, String lieuDep, String lieuArr,LocalDate dateReservation, ReservationStatus reservationStatus) {
        super(id);
        this.reference = reference;
        this.lieuDep = lieuDep;
        this.lieuArr = lieuArr;
        this.dateReservation = dateReservation;
        this.reservationStatus=reservationStatus;

    }


}
