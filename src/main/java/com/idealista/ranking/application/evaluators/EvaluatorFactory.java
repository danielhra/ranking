package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.Evaluator;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class EvaluatorFactory {
    private final List<Evaluator> evaluators;

    public EvaluatorFactory(PictureEvaluator pictureEvaluator,
                            DescriptionEvaluator descriptionEvaluator,
                            CompletionEvaluator completionEvaluator) {
        this.evaluators = List.of(pictureEvaluator, descriptionEvaluator, completionEvaluator);
    }

    public List<Evaluator> getEvaluators() {
        return Collections.unmodifiableList(evaluators);
    }
}
