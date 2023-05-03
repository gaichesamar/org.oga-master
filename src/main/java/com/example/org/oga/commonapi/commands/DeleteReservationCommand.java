package com.example.org.oga.commonapi.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;
@AllArgsConstructor

public class DeleteReservationCommand  {
@TargetAggregateIdentifier
@Getter private String id;

}
