package com.idealista.ranking.motherobjects;

import com.idealista.ranking.adapters.out.vo.AdEntity;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public class AdEntityMother {

    public static AdEntity createAdWithScore() {
        return new AdEntity(1,
                "CHALET",
                "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Flux.just(1, 3),
                300,
                null,
                80,
                LocalDate.now());
    }

    public static AdEntity createAdWithoutScore() {
        return new AdEntity(1,
                "CHALET",
                "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Flux.just(1, 3),
                300,
                150,
                null,null);
    }
}
