package com.idealista.ranking.application.ports.out;

import com.idealista.ranking.domain.Ad;
import reactor.core.publisher.Flux;

public interface CalculateScoreRepository {

    Flux<Ad> getAdsWithoutScore();

    Ad save(Ad capture);
}
