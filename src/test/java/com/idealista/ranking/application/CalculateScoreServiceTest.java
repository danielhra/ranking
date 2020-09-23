package com.idealista.ranking.application;

import com.idealista.ranking.adapters.out.AdPersistenceAdapter;
import com.idealista.ranking.application.evaluators.CompletionEvaluator;
import com.idealista.ranking.application.evaluators.DescriptionEvaluator;
import com.idealista.ranking.application.evaluators.PictureEvaluator;
import com.idealista.ranking.application.ports.out.CalculateScoreRepository;
import com.idealista.ranking.application.evaluators.EvaluatorFactory;
import com.idealista.ranking.domain.Ad;
import com.idealista.ranking.motherobjects.AdMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculateScoreServiceTest {

    private CalculateScoreService sut;
    private CalculateScoreRepository repository;
    private PictureEvaluator pictureEvaluator;

    private DescriptionEvaluator descriptionEvaluator;

    @Captor
    ArgumentCaptor<Ad> adArgumentCaptor;
    private CompletionEvaluator completionEvaluator;

    @BeforeEach
    void setUp() {

        pictureEvaluator = mock(PictureEvaluator.class);
        repository = mock(AdPersistenceAdapter.class);
        descriptionEvaluator = mock(DescriptionEvaluator.class);
        completionEvaluator = mock(CompletionEvaluator.class);
        final var evaluatorFactory = new EvaluatorFactory(pictureEvaluator, descriptionEvaluator, completionEvaluator);
        sut = new CalculateScoreService(repository, evaluatorFactory);
    }

    @Test
    void shouldNotReturnAScoreHigherThanHundred() {
        final Ad ad = AdMother.getPrebuiltAd().build();

        when(repository.getAdsWithoutScore()).thenReturn(Flux.just(ad));
        when(pictureEvaluator.evaluate(ad)).thenReturn(200);
        sut.calculateScore();
        verify(repository).save(adArgumentCaptor.capture());

        assertThat(adArgumentCaptor.getValue()).hasFieldOrPropertyWithValue("score",100);
    }

    @Test
    void shouldCallPictureEvaluator() {
        final Ad ad = AdMother.getPrebuiltAd().build();

        when(repository.getAdsWithoutScore()).thenReturn(Flux.just(ad));
        sut.calculateScore();

        verify(pictureEvaluator).evaluate(ad);

    }

    @Test
    void shouldNotReturnAScoreLowerThanZero() {
        final Ad ad = AdMother.getPrebuiltAd().build();

        when(repository.getAdsWithoutScore()).thenReturn(Flux.just(ad));
        when(pictureEvaluator.evaluate(ad)).thenReturn(-10);
        sut.calculateScore();
        verify(repository).save(adArgumentCaptor.capture());

        assertThat(adArgumentCaptor.getValue()).hasFieldOrPropertyWithValue("score",0);
    }

    @Test
    void shouldCallDescriptionEvaluator() {
        final Ad ad = AdMother.getPrebuiltAd().build();

        when(repository.getAdsWithoutScore()).thenReturn(Flux.just(ad));
        sut.calculateScore();

        verify(descriptionEvaluator).evaluate(ad);

    }
    @Test
    void shouldCallCompletionEvaluator() {
        final Ad ad = AdMother.getPrebuiltAd().build();

        when(repository.getAdsWithoutScore()).thenReturn(Flux.just(ad));
        sut.calculateScore();

        verify(completionEvaluator).evaluate(ad);

    }

    @Test
    void shouldSumAllEvaluators() {

        final Ad ad = AdMother.getPrebuiltAd().build();

        when(repository.getAdsWithoutScore()).thenReturn(Flux.just(ad));
        when(pictureEvaluator.evaluate(ad)).thenReturn(5);
        when(descriptionEvaluator.evaluate(ad)).thenReturn(5);
        when(completionEvaluator.evaluate(ad)).thenReturn(5);
        sut.calculateScore();
        verify(repository).save(adArgumentCaptor.capture());

        assertThat(adArgumentCaptor.getValue()).hasFieldOrPropertyWithValue("score",15);

    }
}
