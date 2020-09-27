package com.idealista.ranking.adapters.in.dto;

import lombok.Builder;
import lombok.Value;
import reactor.core.publisher.Flux;

@Value
public class PublicAd {

    Integer id;
    String typology;
    String description;
    Flux<String> pictureUrls;
    Integer houseSize;
    Integer gardenSize;


}
