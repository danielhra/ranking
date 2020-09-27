package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.Evaluator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class EvaluatorFactory {
    private final Flux<Evaluator> evaluators;

    public EvaluatorFactory(PictureScoreEvaluator pictureScoreEvaluator,
                            DescriptionScoreEvaluator descriptionScoreEvaluator,
                            CompletionScoreEvaluator completionScoreEvaluator) {
        this.evaluators = Flux.just(pictureScoreEvaluator, descriptionScoreEvaluator, completionScoreEvaluator);
    }

    public Flux<Evaluator> getEvaluators() {
        return evaluators;
    }
}
