package com.idealista.ranking.application;

public class CompletionEvaluator extends EvaluatorDecorator {
    public CompletionEvaluator(Evaluator evaluator) {
        super(evaluator);
    }

    @Override
    public int getScoreAmount() {
        return evaluator.getScoreAmount();
    }

    @Override
    public int evaluate() {
        return evaluator.getScoreAmount() + 10;
    }

}
