package com.idealista.ranking.motherobjects;

import com.idealista.ranking.domain.Ad;
import reactor.core.publisher.Flux;

import static com.idealista.ranking.domain.Ad.Typology.CHALET;

public class AdMother {

    public static Ad.AdBuilder getPrebuiltAd() {
        return Ad.builder()
                .id(1)
                .houseSize(300)
                .gardenSize(150)
                .description("Este piso es una ganga, compra, compra, COMPRA!!!!!")
                .irrelevantSince(null)
                .typology(CHALET)
                .score(null)
                .pictures(PictureMother.createPictures().take(2));

    }
}
