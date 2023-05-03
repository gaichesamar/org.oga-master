package com.example.org.oga.commands.controllers;

import com.example.org.oga.commonapi.commands.*;
import com.example.org.oga.commonapi.dtos.*;
import com.example.org.oga.commonapi.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
@RestController
@CrossOrigin(origins = "")
@RequestMapping(path = "/commands/reservation")
@AllArgsConstructor
public class ReservationCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping(path = "/create")
    public CompletableFuture<String> createReservation(@RequestBody CreateReservationRequestDTO request) {
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateReservationCommand(
                UUID.randomUUID().toString(),
                request.getReference(),
                request.getLieuDep(),
                request.getLieuArr(),
                request.getDateReservation(),
                ReservationStatus.PENDING
        ));
        return commandResponse;
    }

    @PostMapping(path = "/accept/{reservationId}")
    public CompletableFuture<String> acceptReservation(@PathVariable String reservationId) {
        CompletableFuture<String> commandResponse = commandGateway.send(new AcceptReservationCommand(reservationId));
        return commandResponse;
    }

    @PostMapping(path = "/reject/{reservationId}")
    public CompletableFuture<String> rejectReservation(@PathVariable String reservationId) {
        CompletableFuture<String> commandResponse = commandGateway.send(new RejectReservationCommand(reservationId));
        return commandResponse;
    }

    @DeleteMapping(path = "/{reservationId}")
    public CompletableFuture<String> deleteReservation(@PathVariable String reservationId) {
        CompletableFuture<String> commandResponse = commandGateway.send(new DeleteReservationCommand(reservationId));
        return commandResponse;
    }

    @GetMapping("/eventStore/{reservationId}")
    public Stream eventStore(@PathVariable String reservationId) {
        return eventStore.readEvents(reservationId).asStream();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        ResponseEntity<String> entity = new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }
}
