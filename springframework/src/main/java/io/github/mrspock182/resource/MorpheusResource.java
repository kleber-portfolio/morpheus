package io.github.mrspock182.resource;

import io.github.mrspock182.service.MartialArtSelectorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1")
public class MorpheusResource {
    private final MartialArtSelectorService service;

    public MorpheusResource(MartialArtSelectorService service) {
        this.service = service;
    }

    @ResponseStatus(OK)
    @GetMapping("/fight")
    public String healthcheck() {
        return service.getArt();
    }
}