package com.idealista.ranking.motherobjects;

import com.idealista.ranking.domain.Ad;

public class AdMother {

    public static Ad.AdBuilder getPrebuiltAd() {
        return Ad.builder()
                .id(1)
                .houseSize(300)
                .gardenSize(150)
                .description("Este piso es una ganga, compra, compra, COMPRA!!!!!")
                .irrelevantSince(null)
                .typology(null)
                .score(null)
                .pictures(PictureMother.createPictures().subList(2, 3));

    }
}
