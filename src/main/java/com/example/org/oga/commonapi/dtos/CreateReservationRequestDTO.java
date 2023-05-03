package com.example.org.oga.commonapi.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CreateReservationRequestDTO {

    private String reference;
    private LocalDate dateReservation;
    private String lieuDep;
    private String lieuArr;

}
