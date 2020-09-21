package com.idealista.ranking.application;

public class DescriptionEvaluator extends EvaluatorDecorator{
    public DescriptionEvaluator(Evaluator evaluator) {
        super(evaluator);
    }

    @Override
    public int getScoreAmount() {
        return evaluator.getScoreAmount();
    }

    @Override
    public int evaluate() {
        return evaluator.getScoreAmount() + 5;
    }

}
