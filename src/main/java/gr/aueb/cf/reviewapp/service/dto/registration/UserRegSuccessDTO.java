package gr.aueb.cf.reviewapp.service.dto.registration;

import gr.aueb.cf.reviewapp.controller.UserRestController;
import gr.aueb.cf.reviewapp.service.dto.insertion.UserInsertDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * The {@link UserRegSuccessDTO} class is returned by the
 * {@link UserRestController} to the frontend if the received {@link UserInsertDTO}
 * is valid and the user is successfully persisted.
 * It inherits from the {@link RegBaseDTO} abstract class.
 * @author geozi
 * @version 1
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegSuccessDTO extends RegBaseDTO {
    private Boolean regStatus = true;
    private String message = "User created";
}
