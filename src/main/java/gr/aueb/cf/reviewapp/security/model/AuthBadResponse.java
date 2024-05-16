package gr.aueb.cf.reviewapp.security.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * The {@link AuthBadResponse} class represents
 * a negative http authentication response.
 * @author geozi
 * @version 1
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthBadResponse extends BaseResponse implements Serializable {

    private String message;
}
