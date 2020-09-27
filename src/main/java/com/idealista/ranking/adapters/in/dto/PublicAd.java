package com.idealista.ranking.adapters.in.dto;

import lombok.Value;

import java.util.List;

@Value
public class PublicAd {

    Integer id;
    String typology;
    String description;
    List<String> pictureUrls;
    Integer houseSize;
    Integer gardenSize;


}
