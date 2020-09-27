package com.idealista.ranking.application.evaluators.strategy;

import com.idealista.ranking.domain.Ad;
import org.springframework.core.codec.AbstractDataBufferDecoder;
import reactor.core.publisher.Mono;

public interface CompletionStrategy {
    Mono<Integer> execute(Ad ad);
}
