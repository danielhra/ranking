package com.idealista.ranking.application;

import com.idealista.ranking.domain.Ad;

public interface Evaluator {

    int evaluate(Ad ad);
}
