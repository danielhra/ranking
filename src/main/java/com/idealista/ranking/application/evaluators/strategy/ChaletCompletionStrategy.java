package com.idealista.ranking.application.evaluators.strategy;

import com.idealista.ranking.application.evaluators.strategy.BaseCompletionStrategy;
import com.idealista.ranking.domain.Ad;

public class ChaletCompletionStrategy extends BaseCompletionStrategy {

    @Override
    boolean typologySpecificFieldsAreCompleted(Ad ad) {
        return ad.getHouseSize() != null && ad.getGardenSize() != null;
    }
}
