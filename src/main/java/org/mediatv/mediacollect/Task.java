package org.mediatv.mediacollect;

import lombok.extern.slf4j.Slf4j;
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

    @Scheduled(cron = "27 12 02 * * *")
    private void scheduledProgramTask() {
        int pages = programService.getProgramPages();
        IntStream.range(0, pages)
                .parallel()
                .forEach(value -> programService.programRequest(value));
    }


}
