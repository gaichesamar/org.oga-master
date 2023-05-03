package com.example.org.oga.commands.aggregates;

import com.example.org.oga.commonapi.commands.CreateReservationCommand;
import com.example.org.oga.commonapi.commands.DeleteReservationCommand;
import com.example.org.oga.commonapi.enums.ReservationStatus;
import com.example.org.oga.commonapi.events.CancelResrvationEvent;
import com.example.org.oga.commonapi.events.ReservationActivatedEvent;
import com.example.org.oga.commonapi.events.ReservationCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

@Aggregate
public class ReservationAggregate {
    @AggregateIdentifier
private String reservationId;
    private LocalDate dateReservation;
    private String lieuDep;
    private String lieuArr;
    private String reference;
    private ReservationStatus status;

    public ReservationAggregate(){
        //Required by AXON
    }
    @CommandHandler
    public ReservationAggregate(CreateReservationCommand createReservationCommand){
        if(createReservationCommand.getReference().isEmpty()) throw new RuntimeException("Impossible");
           //Ok
        AggregateLifecycle.apply(new ReservationCreatedEvent(
               createReservationCommand.getId(),
               createReservationCommand.getReference(),
                createReservationCommand.getDateReservation(),
                createReservationCommand.getLieuDep(),
                createReservationCommand.getLieuArr(),

                ReservationStatus.CREATED

        ));

    }
    @EventSourcingHandler
    public void on(ReservationCreatedEvent event){
        this.reservationId=event.getId();
        this.dateReservation=event.getDateReservation();
        this.lieuDep=event.getLieuDep();
        this.lieuArr=event.getLieuArr();
        this.reference=event.getReference();
        this.status=ReservationStatus.CREATED;
        AggregateLifecycle.apply(new ReservationActivatedEvent(this.reservationId,ReservationStatus.ACTIVATED)
        );
    }
    @EventSourcingHandler
     public void on(ReservationActivatedEvent event){
        this.status=event.getStatus();
    }
    @EventSourcingHandler
    public void handle (CreateReservationCommand command){
        LocalDate dateReservation = LocalDate.now();
    }


    @CommandHandler
    public void handle(DeleteReservationCommand command) {
        AggregateLifecycle.apply(new CancelResrvationEvent(command.getId()));
    }
    @EventSourcingHandler
    public void on(CancelResrvationEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/reservationMS", "postgres", "postgres")) {

            PreparedStatement statement=  connection.prepareStatement("DELETE FROM Car WHERE id = ?");
            statement.setString(1, event.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("pas de connexion");
        }
        this.status = ReservationStatus.CANCELED;

    }



}
