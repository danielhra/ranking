package com.idealista.ranking.application.evaluators.strategy;

import com.idealista.ranking.domain.Ad;

public abstract class BaseCompletionStrategy implements CompletionStrategy {

    public static final int SCORE_FOR_COMPLETED_AD = 40;

    @Override
    public int execute(Ad ad) {
        return commonFieldsAreCompleted(ad) && typologySpecificFieldsAreCompleted(ad) ? SCORE_FOR_COMPLETED_AD : 0;
    }

    private boolean commonFieldsAreCompleted(Ad ad) {
        return
                ad.getPictures() != null
                &&!ad.getPictures().isEmpty()
                && ad.getDescription() != null
                && ad.getDescription().length() > 0;
    }

    abstract boolean typologySpecificFieldsAreCompleted(Ad ad);
}
