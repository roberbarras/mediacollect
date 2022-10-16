package org.mediatv.mediacollect.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collation = "episodes")
public class Episode {

    @MongoId
    private String contentId;

    private String formatId;

    private String title;

    private String mpd;

    private LocalDateTime creationDate;
}
