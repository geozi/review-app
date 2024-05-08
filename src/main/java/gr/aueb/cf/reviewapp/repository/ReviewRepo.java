package gr.aueb.cf.reviewapp.repository;

import gr.aueb.cf.reviewapp.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Reviews.
 * Provides CRUD operations for working with Review documents.
 * @author geozi
 * @version 1
 */
@Repository
public interface ReviewRepo extends MongoRepository<Review, String> {

    List<Review> findAllByUsername(String username);
    List<Review> findAllBySubjectContaining(String subject);
}
