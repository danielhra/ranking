package com.idealista.ranking.application.ports.out;

import com.idealista.ranking.adapters.out.AdPersistenceAdapter;
import com.idealista.ranking.application.CalculateScoreService;
import com.idealista.ranking.motherobjects.AdMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculateScoreServiceTest {

    CalculateScoreService sut;
    CalculateScoreRepository repository;

    @BeforeEach
    void setUp() {

        repository = mock(AdPersistenceAdapter.class);
        sut = new CalculateScoreService(repository);
    }

    @Test
    void name() {

        when(repository.getAdsWithoutScore()).thenReturn(Flux.just(AdMother.createAdWithoutScore()));
        sut.calculateScore();


    }
}
