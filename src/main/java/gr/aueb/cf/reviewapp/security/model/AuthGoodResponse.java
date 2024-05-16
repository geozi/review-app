package gr.aueb.cf.reviewapp.security.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * The {@link AuthGoodResponse} class represents
 * a positive http authentication response.
 * @author geozi
 * @version 1
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthGoodResponse extends BaseResponse implements Serializable {

    private String token;
}
