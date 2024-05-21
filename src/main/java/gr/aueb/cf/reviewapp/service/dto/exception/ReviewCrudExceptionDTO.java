package gr.aueb.cf.reviewapp.service.dto.exception;

import gr.aueb.cf.reviewapp.service.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@link ReviewCrudExceptionDTO} class is a special
 * DTO class for communicating exceptions to the frontend.
 * It inherits from the {@link BaseDTO} abstract class.
 * @author geozi
 * @version 1
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewCrudExceptionDTO extends BaseDTO {

    private String message;
}
