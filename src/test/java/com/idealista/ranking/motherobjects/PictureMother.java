package com.idealista.ranking.motherobjects;

import com.idealista.ranking.domain.Picture;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.idealista.ranking.domain.Picture.Quality.HD;

public class PictureMother {
    public static List<Picture> createPictures() {
        return IntStream.range(0, 5)
                .mapToObj(integer -> new Picture(integer, "mockUrl", HD))
                .collect(Collectors.toList());
    }
}
