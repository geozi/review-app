package gr.aueb.cf.reviewapp.service.validation;

import gr.aueb.cf.reviewapp.service.dto.insertion.ReviewInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * The {@link ReviewInsertValidator} class
 * performs input validation on {@link ReviewInsertDTO}
 * objects. It implements the {@link Validator} interface.
 * @author geozi
 * @version 1
 */
@Component
public class ReviewInsertValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ReviewInsertDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReviewInsertDTO reviewInsertDTO = (ReviewInsertDTO) target;

        String username = reviewInsertDTO.getUsername();
        String subject = reviewInsertDTO.getSubject();
        String description = reviewInsertDTO.getDescription();

        // Username
        if (Objects.equals(username, null)) errors.rejectValue("username", "NotNull");
        ValidationUtils.rejectIfEmpty(errors, "username", "empty");

        // Subject
        if (Objects.equals(subject, null)) errors.rejectValue("subject", "NotNull");
        ValidationUtils.rejectIfEmpty(errors, "subject", "empty");

        // Description
        if (Objects.equals(description, null)) errors.rejectValue("description", "NotNull");
        ValidationUtils.rejectIfEmpty(errors, "description", "empty");

    }
}
