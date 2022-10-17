package org.mediatv.mediacollect.service;

import lombok.extern.slf4j.Slf4j;
import org.mediatv.mediacollect.model.Program;
import org.mediatv.mediacollect.repository.ProgramMongoRepository;
import org.mediatv.mediacollect.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProgramService {

    @Value("${config.program.endpoint}")
    private String url;

    @Autowired
    private WebClient client;

    private final ProgramMongoRepository programMongoRepository;

    public ProgramService(ProgramMongoRepository programMongoRepository) {
        this.programMongoRepository = programMongoRepository;
    }

    public Flux<Program> findAll() {
        return this.programMongoRepository.findAll();
    }

    public Mono<Program> save(Program program) {
        return this.programMongoRepository.save(program);
    }

    public void saveAll(List<Program> programs) {
        this.programMongoRepository.saveAll(programs).subscribe();
    }

    public int getProgramPages() {
        Response response = client.get()
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Response.class))
                .block();

        return Optional.ofNullable(response.getPageInfo().getTotalPages().intValue())
                .orElse(0);
    }

    public void programRequest(int page) {
        client.get()
                .uri(url + page)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Response.class))
                .map(response -> response.getItemRows().stream()
                        .map(item -> Program.builder()
                                .title(item.getTitle())
                                .formatId(item.getFormatId())
                                .creationDate(LocalDateTime.now())
                                .build())
                        .collect(Collectors.toList()))
                .subscribe(this::savePrograms);
    }

    private void savePrograms(List<Program> programs) {
        programs.stream().forEach(program -> log.info("Programa: {}, FormatId: {}", program.getTitle(), program.getFormatId()));
        saveAll(programs);
    }
}
