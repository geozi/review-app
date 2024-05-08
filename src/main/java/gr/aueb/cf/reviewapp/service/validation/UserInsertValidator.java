package gr.aueb.cf.reviewapp.service.validation;

import gr.aueb.cf.reviewapp.model.Role;
import gr.aueb.cf.reviewapp.service.dto.insertion.UserInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * The {@link UserInsertValidator} class
 * performs input validation on {@link UserInsertDTO}
 * objects. It implements the {@link Validator} interface.
 * @author geozi
 * @version 1
 */
@Component
public class UserInsertValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserInsertDTO userInsertDTO = (UserInsertDTO) target;

        String username = userInsertDTO.getUsername();
        String password = userInsertDTO.getPassword();
        Role role = userInsertDTO.getRole();

        // Username
        if(Objects.equals(username, null)) errors.rejectValue("username", "NotNull");
        ValidationUtils.rejectIfEmpty(errors, "username", "empty");

        // Password
        if(Objects.equals(password, null)) errors.rejectValue("password", "NotNull");
        ValidationUtils.rejectIfEmpty(errors, "password", "empty");

        // Role
        if(Objects.equals(role, null)) errors.rejectValue("role", "NotNull");
        ValidationUtils.rejectIfEmpty(errors, "role", "empty");
    }
}
