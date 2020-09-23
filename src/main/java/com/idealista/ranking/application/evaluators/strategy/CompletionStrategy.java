package com.idealista.ranking.application.evaluators.strategy;

import com.idealista.ranking.domain.Ad;
import org.springframework.core.codec.AbstractDataBufferDecoder;

public interface CompletionStrategy {
    int execute(Ad ad);
}
