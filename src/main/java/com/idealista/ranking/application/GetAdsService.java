package com.idealista.ranking.application;

import com.idealista.ranking.application.ports.in.GetPublicAdsUseCase;
import com.idealista.ranking.application.ports.out.GetAdsRepository;
import com.idealista.ranking.domain.Ad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class GetAdsService implements GetPublicAdsUseCase {

    public static final int IRRELEVANT_THRESHOLD = 40;
    private final GetAdsRepository repository;

    @Override
    public Flux<Ad> getPublicAds() {
        return repository.getAdsWithScoreGreaterOrEqualThan(IRRELEVANT_THRESHOLD);
    }
}
