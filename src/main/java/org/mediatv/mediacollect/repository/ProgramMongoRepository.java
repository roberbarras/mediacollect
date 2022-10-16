package org.mediatv.mediacollect.repository;

import org.mediatv.mediacollect.model.Program;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProgramMongoRepository extends ReactiveMongoRepository<Program, String> {
}
