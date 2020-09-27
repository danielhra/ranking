package com.idealista.ranking.application.evaluators.chain;

import com.idealista.ranking.domain.Ad;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface DescriptionEvaluator {

    Mono<Integer> evaluate(Mono<Ad> ad);


}
