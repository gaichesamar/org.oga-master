package com.example.org.oga.queries.services;

import com.example.org.oga.commonapi.events.ReservationActivatedEvent;
import com.example.org.oga.commonapi.events.ReservationCreatedEvent;
import com.example.org.oga.commonapi.query.GetAllResrvationsQuery;
import com.example.org.oga.commonapi.query.GetReservationByIdQuery;
import com.example.org.oga.queries.entities.Reservation;
import com.example.org.oga.queries.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class ReservationServiceHandler {
    private ReservationRepository reservationRepository;
    @EventHandler
    public void on(ReservationCreatedEvent event){
        log.info("**************************");
        log.info("RservationCreatedEvent received");
        Reservation reservation = new Reservation();
        reservation.setId(event.getId());
        reservation.setReference(event.getReference());
        reservation.setDateReservation(event.getDateReservation());
        reservation.setLieuDep(event.getLieuDep());
        reservation.setLieuArr(event.getLieuArr());
        reservation.setStatus(event.getStatus());

        reservationRepository.save(reservation);
    }

    @QueryHandler
    public List<Reservation> on (GetAllResrvationsQuery query)
    {
        return reservationRepository.findAll();
    }
    @QueryHandler
    public Reservation on (GetReservationByIdQuery query)
    {
        return reservationRepository.findById(query.getId()).get();
    }


}
