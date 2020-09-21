package com.idealista.ranking.domain;

import lombok.Value;

@Value
public class Picture {
    Integer id;
    String url;
    Quality quality;

    public enum Quality {HD,SD}
}
