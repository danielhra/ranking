package com.idealista.ranking.application.evaluators.descriptionChain;

import com.idealista.ranking.domain.Ad;

import static com.idealista.ranking.domain.Ad.Typology.CHALET;

public class ChaletDescriptionEvaluatorChain extends DescriptionEvaluatorChain {
    public static final int SCORE_FOR_CHALET_DESCRIPTION_WITH_MORE_THAN_50_WORDS = 20;

    @Override
    public int evaluate(Ad ad) {
        if (CHALET == ad.getTypology()){
            final int score = ad.getScore() + calculateChaletScoreByNumberOfWords(ad.getDescription());
            return super.evaluate(ad.withScore(score));
        }

        return super.evaluate(ad);

    }

    private int calculateChaletScoreByNumberOfWords(String description) {
        return descriptionHasMoreThan50Words(description) ? SCORE_FOR_CHALET_DESCRIPTION_WITH_MORE_THAN_50_WORDS : 0;
    }

    private boolean descriptionHasMoreThan50Words(String description) {
        return description.length() >= 50;
    }


}
