package org.mediatv.mediacollect.service;

import lombok.extern.slf4j.Slf4j;
import org.mediatv.mediacollect.model.Episode;
import org.mediatv.mediacollect.repository.EpisodeMongoRepository;
import org.mediatv.mediacollect.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EpisodeService {

    @Value("${config.episode.endpoint}")
    private String url;

    @Autowired
    private WebClient client;

    private final EpisodeMongoRepository episodeMongoRepository;

    public EpisodeService(EpisodeMongoRepository episodeMongoRepository) {
        this.episodeMongoRepository = episodeMongoRepository;
    }

    public Flux<Episode> findAll() {
        return this.episodeMongoRepository.findAll();
    }

    public Mono<Episode> save(Episode episode) {
        return this.episodeMongoRepository.save(episode);
    }

    public void saveAll(List<Episode> episodes) {
        this.episodeMongoRepository.saveAll(episodes).subscribe();
    }

    public void requestAndSaveEpisodesFromProgram(String formatId) {
        client.get()
                .uri(url.replace("FORMAT_ID", formatId))
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Response.class))
                .map(response -> response.getItemRows().stream()
                        .map(item -> Episode.builder()
                                .title(item.getTitle())
                                .formatId(item.getFormatId())
                                .contentId(item.getContentId())
                                .creationDate(LocalDateTime.now())
                                .build())
                        .collect(Collectors.toList()))
                .subscribe(this::saveEpisodes);
    }

    private void saveEpisodes(List<Episode> episodes) {
        episodes.stream().forEach(episode -> log.info("     Episodio: {}", episode.getTitle()));
        saveAll(episodes);
    }
}
