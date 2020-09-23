package com.idealista.ranking.application.evaluators.chain;

import com.idealista.ranking.domain.Ad;

import static com.idealista.ranking.domain.Ad.Typology.FLAT;

public class FlatDescriptionEvaluatorChain extends DescriptionEvaluatorChain {

    public static final int SCORE_FOR_FLAT_DESCRIPTION_BETWEEN_29_AND_49_WORDS = 10;
    public static final int SCORE_FOR_FLAT_DESCRIPTION_WITH_MORE_THAN_50_WORDS = 30;

    @Override
    public int evaluate(Ad ad) {
        return FLAT == ad.getTypology()
                ? super.evaluate(ad.withScore(calculateFlatScoreByNumberOfWords(ad.getDescription())))
                : super.evaluate(ad);
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
