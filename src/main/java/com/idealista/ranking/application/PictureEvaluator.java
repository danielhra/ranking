package com.idealista.ranking.application;

public class PictureEvaluator extends EvaluatorDecorator{
    public PictureEvaluator(Evaluator evaluator) {
        super(evaluator);
    }

    @Override
    public int getScoreAmount() {
        return evaluator.getScoreAmount();
    }

    @Override
    public void evaluate() {
        return  new Evaluator()
    }

}
