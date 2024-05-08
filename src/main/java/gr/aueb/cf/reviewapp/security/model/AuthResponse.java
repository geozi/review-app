package gr.aueb.cf.reviewapp.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * The {@link AuthResponse} class represents
 * a http authentication response.
 * @author geozi
 * @version 1
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse implements Serializable {

    private String token;
}
