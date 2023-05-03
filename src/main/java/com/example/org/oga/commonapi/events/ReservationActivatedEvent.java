package com.example.org.oga.commonapi.events;

import com.example.org.oga.commonapi.enums.ReservationStatus;
import lombok.Getter;

public class ReservationActivatedEvent extends BaseEvent<String>{
    @Getter private ReservationStatus status ;
    public ReservationActivatedEvent (String id,ReservationStatus status){
       super(id);
       this.status = status;
    }
}
