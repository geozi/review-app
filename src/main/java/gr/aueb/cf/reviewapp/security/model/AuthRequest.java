package gr.aueb.cf.reviewapp.security.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * The {@link AuthRequest} class represents
 * a http authentication request.
 * @author geozi
 * @version 1
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest implements Serializable {

    private String username;
    private String password;
}
