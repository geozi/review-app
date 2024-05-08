package gr.aueb.cf.reviewapp.service.dto.insertion;

import gr.aueb.cf.reviewapp.model.User;
import gr.aueb.cf.reviewapp.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@link UserInsertDTO} class is the DTO
 * equivalent of the {@link User} domain model class,
 * adjusted for insertion operations.
 * @author geozi
 * @version 1
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO {
    private String username;
    private String password;
    private Role role;
}
