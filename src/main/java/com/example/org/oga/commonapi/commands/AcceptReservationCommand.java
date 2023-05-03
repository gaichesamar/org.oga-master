package com.example.org.oga.commonapi.commands;

public class AcceptReservationCommand {
    private final String reservationId;
    public AcceptReservationCommand(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationId() {
        return reservationId;
    }
}