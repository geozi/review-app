package gr.aueb.cf.reviewapp.service.dto.update;

import gr.aueb.cf.reviewapp.model.User;
import gr.aueb.cf.reviewapp.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@link UserUpdateDTO} class is the DTO equivalent of the
 * {@link User} domain model class, adjusted for updating operations.
 * @author geozi
 * @version 1
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateDTO {
    @NotNull
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private Role role;
}
