package com.idealista.ranking.application.evaluators.descriptionEvaluators;

import com.idealista.ranking.domain.Ad;
import reactor.core.publisher.Mono;

public interface DescriptionEvaluator {

    Mono<Integer> evaluate(Mono<Ad> ad);


}
