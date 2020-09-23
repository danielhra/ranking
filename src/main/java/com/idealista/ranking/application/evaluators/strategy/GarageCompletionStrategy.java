package com.idealista.ranking.application.evaluators.strategy;

import com.idealista.ranking.domain.Ad;

public class GarageCompletionStrategy implements CompletionStrategy {

    public static final int SCORE_FOR_AD_COMPLETED = 40;

    @Override
    public int execute(Ad ad) {
        return !ad.getPictures().isEmpty() ? SCORE_FOR_AD_COMPLETED : 0;
    }
}
