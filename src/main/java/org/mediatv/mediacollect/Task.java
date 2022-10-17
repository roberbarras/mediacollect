package org.mediatv.mediacollect;

import lombok.extern.slf4j.Slf4j;
import org.mediatv.mediacollect.service.EpisodeService;
import org.mediatv.mediacollect.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Slf4j
@Component
public class Task {

    @Autowired
    private ProgramService programService;

    @Autowired
    private EpisodeService episodeService;

    //    @Scheduled(fixedDelay = 1000000000)
    private void scheduledProgramTask() {
        int pages = programService.getProgramPages();
        IntStream.range(0, pages)
                .parallel()
                .forEach(value -> programService.programRequest(value));
    }

    @Scheduled(fixedDelay = 1000000000)
    private void scheduledEpisodesTask() {
        programService.findAll().subscribe(program -> {
            log.info("Programa: {}", program.getTitle());
            episodeService.requestAndSaveEpisodesFromProgram(program.getFormatId());
        });
    }


}
