package com.idealista.ranking.motherobjects;

import com.idealista.ranking.adapters.out.vo.AdEntity;
import reactor.core.publisher.Flux;

import java.util.List;

public class AdEntityMother {

    public static AdEntity createAdWithScore() {
        return new AdEntity(1,
                "CHALET",
                "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                List.of(1,3),
                300,
                null,
                80,
                null);
    }

public static AdEntity  createAdWithoutScore() {
        return new AdEntity(1,
                "CHALET",
                "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                List.of(1,3),
                300,
                null,
                null,
                null);
    }
}
