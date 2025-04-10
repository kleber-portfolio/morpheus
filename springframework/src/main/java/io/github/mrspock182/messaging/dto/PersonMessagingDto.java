package io.github.mrspock182.messaging.dto;

import java.time.LocalDate;

public record PersonMessagingDto(
        String id,
        String name,
        LocalDate createIn
) {
}
