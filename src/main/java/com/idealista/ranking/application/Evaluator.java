package com.idealista.ranking.application;

import com.idealista.ranking.domain.Ad;
import reactor.core.publisher.Mono;

public interface Evaluator {

    Mono<Integer> evaluate(Ad ad);
}
