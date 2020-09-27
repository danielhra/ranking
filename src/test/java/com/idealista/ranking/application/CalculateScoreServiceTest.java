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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculateScoreServiceTest {

    @Captor
    ArgumentCaptor<Ad> adArgumentCaptor;
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
        verify(repository).save(adArgumentCaptor.capture());

        assertThat(adArgumentCaptor.getValue()).hasFieldOrPropertyWithValue("score", 100);
    }

    @Test
    void shouldNotReturnAScoreLowerThanZero() {
        final Ad ad = AdMother.getPrebuiltAd().build();

        when(repository.getAdsWithoutScore()).thenReturn(Flux.just(ad));
        when(pictureScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(-10));
        when(descriptionScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(0));
        when(completionScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(0));

        sut.calculateScore();
        verify(repository).save(adArgumentCaptor.capture());

        assertThat(adArgumentCaptor.getValue()).hasFieldOrPropertyWithValue("score", 0);
    }

    @Test
    void shouldSumAllEvaluators() {

        final Ad ad = AdMother.getPrebuiltAd().build();

        when(repository.getAdsWithoutScore()).thenReturn(Flux.just(ad));
        when(pictureScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(5));
        when(descriptionScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(5));
        when(completionScoreEvaluator.evaluate(ad)).thenReturn(Mono.just(5));
        sut.calculateScore();
        verify(repository).save(adArgumentCaptor.capture());

        assertThat(adArgumentCaptor.getValue()).hasFieldOrPropertyWithValue("score", 15);

    }
}
