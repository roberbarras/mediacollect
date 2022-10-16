package org.mediatv.mediacollect.service;

import org.mediatv.mediacollect.model.Episode;
import org.mediatv.mediacollect.repository.EpisodeMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EpisodeService {

    private final EpisodeMongoRepository episodeMongoRepository;

    public EpisodeService(EpisodeMongoRepository episodeMongoRepository) {
        this.episodeMongoRepository = episodeMongoRepository;
    }

    public Flux<Episode> findAll() {
        return this.episodeMongoRepository.findAll();
    }
}
