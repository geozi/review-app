package gr.aueb.cf.reviewapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;


/**
 * The {@link Review} class represents a review
 * posted by a {@link User}.
 * @author geozi
 * @version 1
 */
@Document(collection = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class Review {
    @MongoId
    private String id;
    @Indexed(unique=true)
    private String username;
    private String subject;
    private String description;
    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime modified;

    // Constructors

    public Review(String username, String subject, String description) {
        this.username = username;
        this.subject = subject;
        this.description = description;
    }

    public Review(String id, String username, String subject, String description) {
        this.id = id;
        this.username = username;
        this.subject = subject;
        this.description = description;
    }
}
