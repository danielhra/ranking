package com.idealista.ranking.adapters.out.vo;

import lombok.Value;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Value
public class AdEntity {

    Integer id;
    String typology;
    String description;
    Flux<Integer> pictures;
    Integer houseSize;
    Integer gardenSize;
    Integer score;
    LocalDate irrelevantSince;
}
