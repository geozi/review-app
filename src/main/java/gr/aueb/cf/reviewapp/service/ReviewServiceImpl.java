package gr.aueb.cf.reviewapp.service;

import com.mongodb.DuplicateKeyException;
import gr.aueb.cf.reviewapp.model.Review;
import gr.aueb.cf.reviewapp.repository.ReviewRepo;
import gr.aueb.cf.reviewapp.service.dto.insertion.ReviewInsertDTO;
import gr.aueb.cf.reviewapp.service.dto.update.ReviewUpdateDTO;
import gr.aueb.cf.reviewapp.service.exceptions.DocumentNotFoundException;
import gr.aueb.cf.reviewapp.service.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The {@link ReviewServiceImpl} class implements the
 * CRUD wrapper methods as defined in the {@link IReviewService}
 * interface.
 * @author geozi
 * @version 1
 */
@Service
@Slf4j
public class ReviewServiceImpl implements IReviewService{

    private final ReviewRepo repo;

    @Autowired
    public ReviewServiceImpl(ReviewRepo repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public Review insertReview(ReviewInsertDTO dto) throws Exception {

        Review review;

        try {
            review = repo.save(ReviewMapper.mapToReview(dto));
            if(Objects.equals(review.getId(), null)) throw new Exception("Insertion Error");
        } catch(DuplicateKeyException e) {
            log.info("Duplicate entry error");
            throw e;
        } catch(Exception e) {
            log.info("Insertion Error");
            throw e;
        }

        return review;
    }

    @Override
    @Transactional
    public Review updateReview(ReviewUpdateDTO dto) throws DocumentNotFoundException {

        Optional<Review> optionalReview;
        Review updatedReview;

        try {
            optionalReview = repo.findById(dto.getId());
            if(optionalReview.isEmpty()) throw new DocumentNotFoundException(Review.class, dto.getId());
            updatedReview = repo.save(ReviewMapper.mapToReview(dto));
        } catch(DocumentNotFoundException e) {
            log.info("Update error");
            throw e;
        }

        return updatedReview;
    }

    @Override
    @Transactional
    public Review deleteReview(String id) throws DocumentNotFoundException {

        Optional<Review> optionalReview;
        Review deletedReview;

        try {
            optionalReview = repo.findById(id);
            if(optionalReview.isEmpty()) throw new DocumentNotFoundException(Review.class, id);
            deletedReview = optionalReview.get();
            repo.delete(deletedReview);
        } catch(DocumentNotFoundException e) {
            log.info("Deletion error");
            throw e;
        }
        return deletedReview;
    }

    @Override
    public Review getReviewById(String id) throws DocumentNotFoundException {
        Optional<Review> optionalReview;
        Review review;

        try {
            optionalReview = repo.findById(id);
            if(optionalReview.isEmpty()) throw new DocumentNotFoundException(Review.class, id);
            review = optionalReview.get();
        } catch (DocumentNotFoundException e) {
            log.info("Retrieval error");
            throw e;
        }
        return review;
    }

    @Override
    public List<Review> getAllReviewsByUsername(String username) throws DocumentNotFoundException {
        List<Review> reviews;

        try {
            reviews = repo.findAllByUsername(username);
            if(reviews.isEmpty()) throw new DocumentNotFoundException(Review.class, username);
        } catch (DocumentNotFoundException e) {
            log.info("Multiple document retrieval by username failed");
            throw e;
        }
        return reviews;
    }

    @Override
    public List<Review> getAllReviewsBySubject(String subject) throws Exception {
        List<Review> reviews;

        try {
            reviews = repo.findAllBySubjectContaining(subject);
            if(reviews.isEmpty()) throw new Exception("Documents not found");
        } catch (Exception e) {
            log.info("Multiple document retrieval by subject failed");
            throw e;
        }
        return reviews;
    }
}
