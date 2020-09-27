package com.idealista.ranking.application.evaluators.strategy;

import com.idealista.ranking.domain.Ad;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class GarageCompletionStrategy implements CompletionStrategy {

    public static final int SCORE_FOR_AD_COMPLETED = 40;
    public static final int NO_SCORE = 0;

    @Override
    public Mono<Integer> execute(Ad ad) {

        if(ad.getPictures() == null)
            return Mono.just(NO_SCORE);


        return ad.getPictures()
                .all(Objects::nonNull)
                .map(predicate -> predicate ? SCORE_FOR_AD_COMPLETED : NO_SCORE);
    }
}
