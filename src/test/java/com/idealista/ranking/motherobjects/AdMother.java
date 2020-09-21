package com.idealista.ranking.motherobjects;

import com.idealista.ranking.domain.Ad;

public class AdMother {

    public static Ad createAdWithScore() {
        return Ad.builder()
                .id(1)
                .houseSize(300)
                .gardenSize(150)
                .description("Este piso es una ganga, compra, compra, COMPRA!!!!!")
                .irrelevantSince(null)
                .typology(null)
                .score(null)
                .pictures(PictureMother.createPictures().subList(2, 3))
                .build();


    }

    public static Ad createAdWithoutScore() {
        return Ad.builder()
                .id(1)
                .houseSize(300)
                .gardenSize(150)
                .description("Este piso es una ganga, compra, compra, COMPRA!!!!!")
                .irrelevantSince(null)
                .typology(null)
                .score(80)
                .pictures(PictureMother.createPictures().subList(2, 3))
                .build();

    }
}
