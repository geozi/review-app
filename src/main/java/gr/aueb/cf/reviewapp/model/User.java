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
 * The {@link User} class represents an application User.
 * @author geozi
 * @version 1
 */
@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @MongoId
    private String id;
    @Indexed(unique=true, name = "username_idx")
    private String username;
    private String password;
    private String email;
    private Role role;
    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime modified;

    // Constructors

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(String id, String username, String password, String email, Role role ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
