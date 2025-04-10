package io.github.mrspock182.configuration.service;

import io.github.mrspock182.service.MartialArtSelectorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MartialArtSelectorServiceConfig {
    @Bean
    public MartialArtSelectorService martialArtSelectorService() {
        return new MartialArtSelectorService();
    }
}