package com.example.org.oga.commonapi.commands;

public class RejectReservationCommand {

    private final String reservationId;

    public RejectReservationCommand(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationId() {
        return reservationId;
    }
}