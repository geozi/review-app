package gr.aueb.cf.reviewapp.service.dto.insertion;

import gr.aueb.cf.reviewapp.model.Review;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@link ReviewInsertDTO} class is the DTO
 * equivalent of the {@link Review} domain model class,
 * adjusted for insertion operations.
 * @author geozi
 * @version 1
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewInsertDTO {
    @NotNull
    private String username;
    @NotNull
    private String subject;
    @NotNull
    private String description;
}
