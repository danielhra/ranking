package com.idealista.ranking.application;

import com.idealista.ranking.application.evaluators.CompletionScoreEvaluator;
import com.idealista.ranking.application.evaluators.DescriptionScoreEvaluator;
import com.idealista.ranking.application.evaluators.EvaluatorFactory;
import com.idealista.ranking.application.evaluators.PictureScoreEvaluator;
import com.idealista.ranking.application.ports.out.CalculateScoreRepository;
import com.idealista.ranking.domain.Ad;
import com.idealista.ranking.motherobjects.AdMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateScoreServiceTest {

    @Captor
    ArgumentCaptor<Flux<Ad>> capturedAds;
    @Mock
    private CalculateScoreService sut;
    @Mock
    private CalculateScoreRepository repository;

    @Mock
    private PictureScoreEvaluator pictureScoreEvaluator;
    @Mock
    private DescriptionScoreEvaluator descriptionScoreEvaluator;
    @Mock
    private CompletionScoreEvaluator completionScoreEvaluator;

    @BeforeEach
    void setUp() {
        BlockHound.install();
        final var evaluatorFactory = new EvaluatorFactory(pictureScoreEvaluator, descriptionScoreEvaluator, completionScoreEvaluator);
        sut = new CalculateScoreService(repository, evaluatorFactory);
    }

    @Test
    void shouldNotReturnAScoreHigherThanHundred() {
        final Ad ad = AdMother.getPrebuiltAd().build();

        when(repository.getAdsWithoutScore()).thenReturn(Flux.just(ad));
        when(pictureScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(200));
        when(descriptionScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(0));
        when(completionScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(0));
        sut.calculateScore();
        verify(repository).saveAll(capturedAds.capture());

        StepVerifier.create(capturedAds.getValue())
                .expectNextMatches(a -> a.getScore().equals(100))
                .verifyComplete();

    }

    @Test
    void shouldNotReturnAScoreLowerThanZero() {
        final Ad ad = AdMother.getPrebuiltAd().build();

        when(repository.getAdsWithoutScore()).thenReturn(Flux.just(ad));
        when(pictureScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(-10));
        when(descriptionScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(0));
        when(completionScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(0));

        sut.calculateScore();
        verify(repository).saveAll(capturedAds.capture());

        StepVerifier.create(capturedAds.getValue())
                .expectNextMatches(a -> a.getScore().equals(0))
                .verifyComplete();
    }

    @Test
    void shouldSumAllEvaluators() {

        final Ad ad = AdMother.getPrebuiltAd().build();

        when(repository.getAdsWithoutScore()).thenReturn(Flux.just(ad));
        when(pictureScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(5));
        when(descriptionScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(5));
        when(completionScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(5));
        sut.calculateScore();
        verify(repository).saveAll(capturedAds.capture());

        StepVerifier.create(capturedAds.getValue())
                .expectNextMatches(a -> a.getScore().equals(15))
                .verifyComplete();
    }

}
