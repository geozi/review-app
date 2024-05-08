package gr.aueb.cf.reviewapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * The {@link Review} class represents a review
 * posted by a User.
 * @author geozi
 * @version 1
 */
@Document(collection = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @MongoId
    private String id;
    @Indexed(unique=true)
    private String username;
    private String subject;
    private String description;

    // Constructor

    public Review(String username, String subject, String description) {
        this.username = username;
        this.subject = subject;
        this.description = description;
    }
}
