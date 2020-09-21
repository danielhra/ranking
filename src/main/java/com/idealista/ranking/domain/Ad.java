package com.idealista.ranking.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;

@Value
@Builder
public class Ad {

    Integer id;
    String typology;
    String description;
    List<Picture> pictures;
    Integer houseSize;
    Integer gardenSize;

    @With
    Integer score;
    Date irrelevantSince;
}
