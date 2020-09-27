package com.idealista.ranking.adapters.in.dto;

import lombok.Builder;
import lombok.Value;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;

@Value
@Builder
public class QualityAd {

    Integer id;
    String typology;
    String description;
    Flux<String> pictureUrls;
    Integer houseSize;
    Integer gardenSize;
    Integer score;
    Date irrelevantSince;
}
