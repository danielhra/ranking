package com.idealista.ranking.application.evaluators.strategy;

import com.idealista.ranking.domain.Ad;
import reactor.core.publisher.Mono;

import java.util.Objects;

public abstract class BaseCompletionStrategy implements CompletionStrategy {

    public static final int SCORE_FOR_COMPLETED_AD = 40;

    @Override
    public Mono<Integer> execute(Ad ad) {
        return commonFieldsAreCompleted(ad)
                .map(predicate -> predicate && typologySpecificFieldsAreCompleted(ad))
                .map(predicate -> predicate ? SCORE_FOR_COMPLETED_AD : 0);
    }

    private Mono<Boolean> commonFieldsAreCompleted(Ad ad) {

        if(ad.getPictures() == null)
            return Mono.just(false);

        return ad.getPictures().all(Objects::nonNull)
                .map(predicate ->
                        predicate && ad.getDescription() != null
                                && ad.getDescription().length() > 0);


    }

    abstract boolean typologySpecificFieldsAreCompleted(Ad ad);
}
