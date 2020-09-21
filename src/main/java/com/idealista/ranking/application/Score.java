package com.idealista.ranking.application;

public class Score implements Evaluator {

    private final int scoreAmount;

    public Score(int scoreAmount) {
        this.scoreAmount = scoreAmount;
    }

    @Override
    public int getScoreAmount() {
        return scoreAmount;
    }

    @Override
    public void setScoreAmount() {
        scoreAmount =
    }

    @Override
    public int evaluate() {
        return Math.max(scoreAmount,0);
    }
}
