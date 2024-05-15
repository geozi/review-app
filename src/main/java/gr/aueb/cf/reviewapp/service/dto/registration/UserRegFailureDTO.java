package gr.aueb.cf.reviewapp.service.dto.registration;

import gr.aueb.cf.reviewapp.controller.UserRestController;
import gr.aueb.cf.reviewapp.service.dto.insertion.UserInsertDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * The {@link UserRegFailureDTO} class is returned by the
 * {@link UserRestController} to the frontend if the received {@link UserInsertDTO}
 * contains a username that is already persisted.
 * It inherits from the {@link RegBaseDTO} abstract class.
 * @author geozi
 * @version 1
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegFailureDTO extends RegBaseDTO {
    private Boolean regStatus = false;
    private String message = "Username is in use";
}
