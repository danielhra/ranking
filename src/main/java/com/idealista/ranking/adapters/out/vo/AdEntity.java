package com.idealista.ranking.adapters.out.vo;

import lombok.Value;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;

@Value
public class AdEntity {

    Integer id;
    String typology;
    String description;
    List<Integer> pictures;
    Integer houseSize;
    Integer gardenSize;
    Integer score;
    Date irrelevantSince;
}
