package gr.aueb.cf.reviewapp.service.validation;

import gr.aueb.cf.reviewapp.service.dto.update.ReviewUpdateDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * The {@link ReviewUpdateValidator} class
 * performs input validation on {@link ReviewUpdateDTO}
 * objects. It implements the {@link Validator} interface.
 * @author geozi
 * @version 1
 */
@Component
public class ReviewUpdateValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ReviewUpdateDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReviewUpdateDTO reviewUpdateDTO = (ReviewUpdateDTO) target;

        String id = reviewUpdateDTO.getId();
        String username = reviewUpdateDTO.getUsername();
        String subject = reviewUpdateDTO.getSubject();
        String description = reviewUpdateDTO.getDescription();

        //Id
        if (Objects.equals(id, null)) errors.rejectValue("id", "NotNull");
        ValidationUtils.rejectIfEmpty(errors, "id", "empty");

        // Username
        if (Objects.equals(username, null)) errors.rejectValue("username", "NotNull");
        ValidationUtils.rejectIfEmpty(errors, "id", "NotNull");

        // Subject
        if (Objects.equals(subject, null)) errors.rejectValue("subject", "NotNull");
        ValidationUtils.rejectIfEmpty(errors, "subject", "empty");

        // Description
        if (Objects.equals(description, null)) errors.rejectValue("description", "NotNull");
        ValidationUtils.rejectIfEmpty(errors, "description", "empty");

    }

}
