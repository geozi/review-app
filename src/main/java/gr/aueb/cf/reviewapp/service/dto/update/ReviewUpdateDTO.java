package gr.aueb.cf.reviewapp.service.dto.update;

import gr.aueb.cf.reviewapp.model.Review;
import gr.aueb.cf.reviewapp.service.dto.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@link ReviewUpdateDTO} class is the DTO equivalent of the
 * {@link Review} domain model class, adjusted for updating operations.
 * It inherits from the {@link BaseDTO} abstract class.
 * @author geozi
 * @version 1
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewUpdateDTO extends BaseDTO {
    @NotNull
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String subject;
    @NotNull
    private String description;
}
