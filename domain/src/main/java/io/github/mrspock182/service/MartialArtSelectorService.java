package io.github.mrspock182.service;

import io.github.mrspock182.entity.enumerable.MartialArtsEnum;

import java.util.concurrent.ThreadLocalRandom;

public class MartialArtSelectorService {
    public String getArt() {
        final MartialArtsEnum[] values = MartialArtsEnum.values();
        return values[ThreadLocalRandom.current().nextInt(values.length)].toString();
    }
}