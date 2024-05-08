package gr.aueb.cf.reviewapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Set;

/**
 * The {@link User} class represents an app User.
 * @author geozi
 * @version 1
 */
@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @MongoId
    private String id;
    @Indexed(unique=true, name = "username_idx")
    private String username;
    private String password;
    private Role role;

    // Constructor

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
