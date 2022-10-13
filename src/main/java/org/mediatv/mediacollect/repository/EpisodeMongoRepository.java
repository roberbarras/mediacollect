package org.mediatv.mediacollect.repository;

import org.mediatv.mediacollect.model.Episode;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EpisodeMongoRepository extends ReactiveMongoRepository<Episode, String> {
}
