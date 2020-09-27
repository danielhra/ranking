package com.idealista.ranking.application.evaluators.strategy;

import com.idealista.ranking.domain.Ad;

public class FlatCompletionStrategy extends BaseCompletionStrategy {

    @Override
    boolean typologySpecificFieldsAreCompleted(Ad ad) {
        return ad.getHouseSize() != null;

    }
}
