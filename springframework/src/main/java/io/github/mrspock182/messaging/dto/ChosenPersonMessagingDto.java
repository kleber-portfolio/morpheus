package io.github.mrspock182.messaging.dto;

import io.github.mrspock182.entity.enumerable.PillEnum;

import java.time.LocalDate;

public record ChosenPersonMessagingDto(
        String id,
        String name,
        PillEnum pill,
        LocalDate createIn
) {
}
