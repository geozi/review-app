package gr.aueb.cf.reviewapp.service.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@link UserRegDTO} class is a special
 * DTO class for communicating the result of the
 * registration process to the frontend.
 * @author geozi
 * @version 1
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegDTO {

    private String message;
}
