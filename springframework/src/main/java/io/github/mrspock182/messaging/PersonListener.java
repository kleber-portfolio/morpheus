package io.github.mrspock182.messaging;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.mrspock182.entity.Person;
import io.github.mrspock182.messaging.adapter.PersonMessagingAdapter;
import io.github.mrspock182.messaging.dto.PersonMessagingDto;
import io.github.mrspock182.service.PillSelectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PersonListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChosenPersonProducerWithSqs.class);

    private final PillSelectorService pillSelectorService;

    public PersonListener(PillSelectorService pillSelectorService) {
        this.pillSelectorService = pillSelectorService;
    }

    @SqsListener("${app.queues.person}")
    public void listener(final PersonMessagingDto message) {
        final Person person = PersonMessagingAdapter.cast(message);
        pillSelectorService.choose(person);
    }
}