package com.idealista.ranking.application.evaluators.descriptionEvaluators;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class DescriptionEvaluatorFactory {

    public Flux<DescriptionEvaluator> get() {
        return Flux.just(
                new ChaletDescriptionScoreEvaluator(),
                new FlatDescriptionScoreEvaluator(),
                new MatchingWordsScoreEvaluator()
        );
    }


}
