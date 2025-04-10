package io.github.mrspock182.configuration.service;

import io.github.mrspock182.messaging.ChosenPersonProducer;
import io.github.mrspock182.repository.PersonRepository;
import io.github.mrspock182.service.PillSelectorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PillSelectorServiceConfig {
    @Bean
    public PillSelectorService pillSelectorService(
            final ChosenPersonProducer personProducer,
            final PersonRepository personRepository) {
        return new PillSelectorService(personProducer, personRepository);
    }
}