package io.github.mrspock182.messaging.adapter;

import io.github.mrspock182.entity.Person;
import io.github.mrspock182.messaging.dto.PersonMessagingDto;

public class PersonMessagingAdapter {
    private PersonMessagingAdapter() {
    }

    public static Person cast(final PersonMessagingDto messaging) {
        return new Person(
                messaging.id(),
                messaging.name(),
                null,
                "",
                messaging.createIn());
    }
}
