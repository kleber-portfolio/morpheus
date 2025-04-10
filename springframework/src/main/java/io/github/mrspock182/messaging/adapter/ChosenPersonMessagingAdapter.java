package io.github.mrspock182.messaging.adapter;

import io.github.mrspock182.entity.Person;
import io.github.mrspock182.messaging.dto.ChosenPersonMessagingDto;

public class ChosenPersonMessagingAdapter {
    private ChosenPersonMessagingAdapter() {
    }

    public static ChosenPersonMessagingDto cast(final Person person) {
        return new ChosenPersonMessagingDto(
                person.id(),
                person.name(),
                person.pill(),
                person.createIn());
    }
}
