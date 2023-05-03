package com.example.org.oga.commonapi.events;

import com.example.org.oga.commonapi.enums.ReservationStatus;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CancelResrvationEvent {
    @TargetAggregateIdentifier
    @Getter
    private String id;
    @Getter
    private ReservationStatus status;

    public CancelResrvationEvent(String id) {
        this.id=id;
        this.status = ReservationStatus.CANCELED;
    }


}
