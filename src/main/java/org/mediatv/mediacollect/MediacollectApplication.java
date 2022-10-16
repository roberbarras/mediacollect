package org.mediatv.mediacollect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MediacollectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediacollectApplication.class, args);
    }

}
