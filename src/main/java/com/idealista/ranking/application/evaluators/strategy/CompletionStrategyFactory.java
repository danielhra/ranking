package com.idealista.ranking.application.evaluators.strategy;

import com.idealista.ranking.domain.Ad;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.idealista.ranking.domain.Ad.Typology.*;

@Component
public class CompletionStrategyFactory {

    Map<Ad.Typology, CompletionStrategy> strategies;

    public CompletionStrategyFactory() {
        strategies = Map.of(
                CHALET, new ChaletCompletionStrategy(),
                FLAT, new FlatCompletionStrategy(),
                GARAGE, new GarageCompletionStrategy()
        );
    }

    public CompletionStrategy chooseStrategyBy(Ad.Typology typology){
        return typology != null ? strategies.getOrDefault(typology, ad -> 0) : (ad -> 0);
    }


}
