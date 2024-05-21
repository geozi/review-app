package gr.aueb.cf.reviewapp.service.mapper;

import gr.aueb.cf.reviewapp.model.Review;
import gr.aueb.cf.reviewapp.service.dto.insertion.ReviewInsertDTO;
import gr.aueb.cf.reviewapp.service.dto.readonly.ReviewReadOnlyDTO;
import gr.aueb.cf.reviewapp.service.dto.update.ReviewUpdateDTO;
import org.springframework.stereotype.Component;

/**
 * The {@link ReviewMapper} class is a utility class
 * that maps DTOs to the Review domain model class and vice versa.
 * @author geozi
 * @version 1
 */
@Component
public class ReviewMapper {

    private ReviewMapper() {}

    public static Review mapToReview(ReviewInsertDTO dto) {
        return new Review(dto.getUsername(), dto.getSubject(), dto.getDescription());
    }

    public static Review mapToReview(ReviewUpdateDTO dto) {
        return new Review(dto.getId(), dto.getUsername(), dto.getSubject(), dto.getDescription());
    }

    public static Review mapToReview(ReviewReadOnlyDTO dto) {
        return new Review(dto.getId(), dto.getUsername(), dto.getSubject(), dto.getDescription());
    }

    public static ReviewReadOnlyDTO mapToReviewReadOnlyDTO(Review review) {
        return new ReviewReadOnlyDTO(review.getId(), review.getUsername(), review.getSubject(), review.getDescription());
    }

}
