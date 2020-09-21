package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.Evaluator;
import com.idealista.ranking.domain.Ad;

import static com.idealista.ranking.domain.Picture.Quality.SD;

public class PictureEvaluator implements Evaluator {

    public static final int LACK_OF_PICTURE_SCORE = -10;
    public static final int SD_PICTURE_SCORE = 10;
    public static final int HD_PICTURE_SCORE = 20;

    @Override
    public int evaluate(Ad ad) {
        if (lackPictures(ad)) {
            return LACK_OF_PICTURE_SCORE;
        }

        return ad.getPictures()
                .stream()
                .mapToInt(picture -> picture.getQuality().equals(SD) ? SD_PICTURE_SCORE : HD_PICTURE_SCORE)
                .sum();
    }

    private boolean lackPictures(Ad ad) {
        return ad.getPictures() == null || ad.getPictures().isEmpty();
    }
}
