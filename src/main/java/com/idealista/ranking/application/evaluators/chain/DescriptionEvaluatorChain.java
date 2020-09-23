package com.idealista.ranking.application.evaluators.chain;

import com.idealista.ranking.domain.Ad;

import java.util.Optional;

public class DescriptionEvaluatorChain {

    private DescriptionEvaluatorChain next;


    public DescriptionEvaluatorChain linkNext(DescriptionEvaluatorChain next){
        if (getNext().isPresent()) {
            this.next.linkNext(next);
        } else {
            this.next = next;
        }

        return this;
    }

    protected Optional<DescriptionEvaluatorChain> getNext() {
        return Optional.ofNullable(next);
    }

    public int evaluate(Ad ad){
        return getNext().isPresent() ? (ad.getScore() + next.evaluate(ad)) : ad.getScore();

    }







}
