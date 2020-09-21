package com.idealista.ranking.motherobjects;

import com.idealista.ranking.domain.Picture;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PictureMother {
    public static List<Picture> createPictures() {
        return IntStream.range(0, 5)
                .mapToObj(integer -> new Picture(integer, "mockUrl", "mockQuality"))
                .collect(Collectors.toList());
    }
}
