package com.idealista.ranking.application.evaluators.descriptionChain;

import com.idealista.ranking.domain.Ad;

import java.util.Optional;

public abstract class DescriptionEvaluatorChain {

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
        return getNext().isPresent() ? next.evaluate(ad) : ad.getScore();

    }







}
