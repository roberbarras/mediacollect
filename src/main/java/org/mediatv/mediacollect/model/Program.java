package org.mediatv.mediacollect.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document (collection = "programs")
public class Program implements Serializable {

    @MongoId
    private String formatId;

    private String title;

    private LocalDateTime creationDate;
}
