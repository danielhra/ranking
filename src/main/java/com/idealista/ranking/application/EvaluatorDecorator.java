package com.idealista.ranking.application;

public abstract class EvaluatorDecorator implements Evaluator{
    
    protected Evaluator evaluator;

    public EvaluatorDecorator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public abstract int evaluate();
}
