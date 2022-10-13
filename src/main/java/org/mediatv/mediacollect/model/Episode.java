package org.mediatv.mediacollect.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collation = "episodes")
public class Episode {

    @Id
    private String contentId;

    private String formatId;

    private String title;

    private String mpd;

    private LocalDateTime creationDate;
}
