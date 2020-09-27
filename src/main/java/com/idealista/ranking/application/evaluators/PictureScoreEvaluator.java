package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.Evaluator;
import com.idealista.ranking.domain.Ad;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.idealista.ranking.domain.Picture.Quality.SD;

@Component
public class PictureScoreEvaluator implements Evaluator {

    public static final int LACK_OF_PICTURE_SCORE = -10;
    public static final int SD_PICTURE_SCORE = 10;
    public static final int HD_PICTURE_SCORE = 20;

    @Override
    public Mono<Integer> evaluate(Ad ad) {
        if (ad.getPictures() == null) {
            return Mono.just(LACK_OF_PICTURE_SCORE);
        }

        return ad.getPictures()
                .map(picture -> picture.getQuality().equals(SD) ? SD_PICTURE_SCORE : HD_PICTURE_SCORE)
                .defaultIfEmpty(LACK_OF_PICTURE_SCORE)
                .reduce(Integer::sum);

    }

}
