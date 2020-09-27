package com.idealista.ranking.application.evaluators.descriptionEvaluators;

import com.idealista.ranking.domain.Ad;
import reactor.core.publisher.Mono;

import static com.idealista.ranking.domain.Ad.Typology.CHALET;


public class ChaletDescriptionScoreEvaluator implements DescriptionEvaluator {
    public static final int SCORE_FOR_CHALET_DESCRIPTION_WITH_MORE_THAN_50_WORDS = 20;

    @Override
    public Mono<Integer> evaluate(Mono<Ad> ad) {
        return ad
                .filter(a -> CHALET == a.getTypology())
                .map(a -> calculateChaletScoreByNumberOfWords(a.getDescription()));
    }

    private int calculateChaletScoreByNumberOfWords(String description) {
        return descriptionHasMoreThan50Words(description) ? SCORE_FOR_CHALET_DESCRIPTION_WITH_MORE_THAN_50_WORDS : 0;
    }

    private boolean descriptionHasMoreThan50Words(String description) {
        return description.length() >= 50;
    }


}
