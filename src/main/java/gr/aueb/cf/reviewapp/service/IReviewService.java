package gr.aueb.cf.reviewapp.service;

import gr.aueb.cf.reviewapp.service.dto.insertion.ReviewInsertDTO;
import gr.aueb.cf.reviewapp.model.Review;
import gr.aueb.cf.reviewapp.service.dto.update.ReviewUpdateDTO;
import gr.aueb.cf.reviewapp.service.exceptions.DocumentNotFoundException;

import java.util.List;

/**
 * The {@link IReviewService} interface contains
 * the service-layer definitions of the CRUD methods
 * used for managing Review documents.
 */
public interface IReviewService {
    Review insertReview(ReviewInsertDTO dto) throws Exception;
    Review updateReview(ReviewUpdateDTO dto) throws DocumentNotFoundException;
    Review deleteReview(String id) throws DocumentNotFoundException;
    Review getReviewById(String id) throws DocumentNotFoundException;
    List<Review> getAllReviewsBySubject(String subject) throws Exception;
    List<Review> getAllReviewsByUsername(String username) throws DocumentNotFoundException;
}
