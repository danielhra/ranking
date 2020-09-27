package com.idealista.ranking.application;

import com.idealista.ranking.application.ports.out.GetAdsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetAdsServiceTest {

    GetAdsService sut;
    GetAdsRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(GetAdsRepository.class);
        sut = new GetAdsService(repository);
    }

    @Test
    void shouldGetAdsWithScoreGreaterThan40() {
        sut.getPublicAds();
        verify(repository).getAdsWithScoreGreaterOrEqualThan(40);
    }
}
