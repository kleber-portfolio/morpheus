package io.github.mrspock182.service;

import io.github.mrspock182.entity.Person;
import io.github.mrspock182.entity.enumerable.PillEnum;
import io.github.mrspock182.messaging.ChosenPersonProducer;
import io.github.mrspock182.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class PillSelectorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PillSelectorService.class);

    private final ChosenPersonProducer personProducer;
    private final PersonRepository personRepository;

    public PillSelectorService(
            ChosenPersonProducer personProducer,
            PersonRepository personRepository) {
        this.personProducer = personProducer;
        this.personRepository = personRepository;
    }

    public void choose(final Person person) {
        try {
            Optional.ofNullable(person)
                    .map(this::getPill)
                    .ifPresent(p -> {
                        PillEnum pill = p.pill();
                        LOGGER.info("Pill is {}", pill);
                        if (pill == PillEnum.BLUE) {
                            personRepository.save(p);
                        } else {
                            personProducer.produce(p);
                        }
                    });
        } catch (Exception ex) {
            LOGGER.error("Erro no processo morpheus", ex);
            throw new RuntimeException("Failed to process message");
        }
    }

    private Person getPill(final Person person) {
        final PillEnum pill = ThreadLocalRandom.current()
                .nextInt(PillEnum.values().length) == 0 ? PillEnum.BLUE : PillEnum.RED;
        final String message = pill == PillEnum.BLUE ? "Wake UP!" : "Free your mind.";
        return new Person(
                person.id(),
                person.name(),
                pill,
                message,
                person.createIn());
    }

}