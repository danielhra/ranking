package com.idealista.ranking.motherobjects;

import com.idealista.ranking.domain.Picture;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.idealista.ranking.domain.Picture.Quality.HD;

public class PictureMother {
    public static Flux<Picture> createPictures() {
        return Flux.range(1, 5)
                .map(integer -> new Picture(integer, "mockUrl", HD));
    }
}
