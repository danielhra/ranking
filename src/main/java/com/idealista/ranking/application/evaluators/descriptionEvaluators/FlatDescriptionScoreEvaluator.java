package com.idealista.ranking.application.evaluators.descriptionEvaluators;

import com.idealista.ranking.domain.Ad;
import reactor.core.publisher.Mono;

import static com.idealista.ranking.domain.Ad.Typology.FLAT;

public class FlatDescriptionScoreEvaluator implements DescriptionEvaluator {

    public static final int SCORE_FOR_FLAT_DESCRIPTION_BETWEEN_29_AND_49_WORDS = 10;
    public static final int SCORE_FOR_FLAT_DESCRIPTION_WITH_MORE_THAN_50_WORDS = 30;

    @Override
    public Mono<Integer> evaluate(Mono<Ad> ad) {
        return ad.filter(a -> FLAT == a.getTypology())
                .map(a -> calculateFlatScoreByNumberOfWords(a.getDescription()));

    }

    private int calculateFlatScoreByNumberOfWords(String description) {
        if (descriptionHasBetween29And49Words(description)) {
            return SCORE_FOR_FLAT_DESCRIPTION_BETWEEN_29_AND_49_WORDS;
        } else if (descriptionHasMoreThan50Words(description)) {
            return SCORE_FOR_FLAT_DESCRIPTION_WITH_MORE_THAN_50_WORDS;
        }
        return 0;
    }

    private boolean descriptionHasMoreThan50Words(String description) {
        return description.length() >= 50;
    }

    private boolean descriptionHasBetween29And49Words(String description) {
        return description.length() >= 29 && description.length() <= 49;
    }


}
