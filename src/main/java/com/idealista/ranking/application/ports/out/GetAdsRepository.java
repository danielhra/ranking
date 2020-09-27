package com.idealista.ranking.application.ports.out;

import com.idealista.ranking.domain.Ad;
import reactor.core.publisher.Flux;

public interface GetAdsRepository {
    Flux<Ad> getAdsWithScoreGreaterOrEqualThan(int score);
}
