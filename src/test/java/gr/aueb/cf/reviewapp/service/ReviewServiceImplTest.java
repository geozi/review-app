package gr.aueb.cf.reviewapp.service;

import gr.aueb.cf.reviewapp.model.Review;
import gr.aueb.cf.reviewapp.repository.ReviewRepo;
import gr.aueb.cf.reviewapp.service.dto.insertion.ReviewInsertDTO;
import gr.aueb.cf.reviewapp.service.dto.update.ReviewUpdateDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class ReviewServiceImplTest {
    @Autowired
    private ReviewRepo repo;
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        reviewService = new ReviewServiceImpl(repo);
    }

    @AfterEach
    void tearDown() {
        repo.deleteAll();
    }

    @Test
    void insertReview() throws Exception {
        // Arrange
        ReviewInsertDTO insertDTO = new ReviewInsertDTO();
        insertDTO.setUsername("71de");
        insertDTO.setSubject("Example subject");
        insertDTO.setDescription("This is a review posted by chronos");

        // Act
        Review insertedReview = reviewService.insertReview(insertDTO);

        // Assert
        assertAll(
                () -> assertNotNull(insertedReview),
                () -> assertNotNull(insertedReview.getId()),
                () -> assertNotNull(insertedReview.getUsername()),
                () -> assertNotNull(insertedReview.getSubject()),
                () -> assertNotNull(insertedReview.getDescription()),
                () -> assertFalse(insertedReview.getId().isEmpty()),
                () -> assertFalse(insertedReview.getUsername().isEmpty()),
                () -> assertFalse(insertedReview.getSubject().isEmpty()),
                () -> assertFalse(insertedReview.getDescription().isEmpty()),
                () -> assertEquals("71de", insertedReview.getUsername()),
                () -> assertEquals("Example subject", insertedReview.getSubject()),
                () -> assertEquals("This is a review posted by chronos", insertedReview.getDescription())
        );


    }

    @Test
    void updateReview() throws Exception {
        // Arrange
        ReviewInsertDTO insertDTO = new ReviewInsertDTO();
        insertDTO.setUsername("fbdc60");
        insertDTO.setSubject("Same subject");
        insertDTO.setDescription("I really liked your product! It was awesome");

        // Act
        Review insertedReview = reviewService.insertReview(insertDTO);

        // Arrange
        ReviewUpdateDTO updateDTO = new ReviewUpdateDTO();
        updateDTO.setId(insertedReview.getId());
        updateDTO.setUsername("69c85");
        updateDTO.setSubject("Same subject");
        updateDTO.setDescription("This is an updated description!");

        // Act
        Review updatedReview = reviewService.updateReview(updateDTO);

        // Assert
        assertAll(
                () -> assertNotNull(updatedReview),
                () -> assertNotNull(updatedReview.getId()),
                () -> assertNotNull(updatedReview.getUsername()),
                () -> assertNotNull(updatedReview.getSubject()),
                () -> assertNotNull(updatedReview.getDescription()),
                () -> assertFalse(updatedReview.getId().isEmpty()),
                () -> assertFalse(updatedReview.getUsername().isEmpty()),
                () -> assertFalse(updatedReview.getSubject().isEmpty()),
                () -> assertFalse(updatedReview.getDescription().isEmpty()),
                () -> assertNotEquals("I really liked your product! It was awesome", updatedReview.getDescription()),
                () -> assertEquals("69c85", updatedReview.getUsername()),
                () -> assertEquals("Same subject", updatedReview.getSubject()),
                () -> assertEquals("This is an updated description!", updatedReview.getDescription())
        );
    }

    @Test
    void deleteReview() throws Exception {

        // Arrange
        ReviewInsertDTO insertDTO = new ReviewInsertDTO();
        insertDTO.setUsername("81bfaa");
        insertDTO.setSubject("Testing deletion");
        insertDTO.setDescription("This is test document");

        // Act
        Review insertedReview = reviewService.insertReview(insertDTO);
        Review deletedReview = reviewService.deleteReview(insertedReview.getId());

        // Assert
        assertAll(
                () -> assertNotNull(deletedReview),
                () -> assertNotNull(deletedReview.getId()),
                () -> assertNotNull(deletedReview.getUsername()),
                () -> assertNotNull(deletedReview.getSubject()),
                () -> assertNotNull(deletedReview.getDescription()),
                () -> assertFalse(deletedReview.getId().isEmpty()),
                () -> assertFalse(deletedReview.getUsername().isEmpty()),
                () -> assertFalse(deletedReview.getSubject().isEmpty()),
                () -> assertFalse(deletedReview.getDescription().isEmpty()),
                () -> assertEquals("81bfaa", deletedReview.getUsername()),
                () -> assertEquals("Testing deletion", deletedReview.getSubject()),
                () -> assertEquals("This is test document", deletedReview.getDescription())
        );
    }

    @Test
    void getReviewById() throws Exception{

        // Arrange
        ReviewInsertDTO insertDTO = new ReviewInsertDTO();
        insertDTO.setUsername("282aa91");
        insertDTO.setSubject("Document retrieval");
        insertDTO.setDescription("Testing document retrieval by Id");

        // Act
        Review insertedReview = reviewService.insertReview(insertDTO);
        Review review = reviewService.getReviewById(insertedReview.getId());

        // Assert
        assertAll(
                () -> assertNotNull(review),
                () -> assertNotNull(review.getId()),
                () -> assertNotNull(review.getUsername()),
                () -> assertNotNull(review.getSubject()),
                () -> assertNotNull(review.getDescription()),
                () -> assertFalse(review.getId().isEmpty()),
                () -> assertFalse(review.getUsername().isEmpty()),
                () -> assertFalse(review.getSubject().isEmpty()),
                () -> assertFalse(review.getDescription().isEmpty()),
                () -> assertEquals("282aa91", review.getUsername()),
                () -> assertEquals("Document retrieval", review.getSubject()),
                () -> assertEquals("Testing document retrieval by Id", review.getDescription())
        );
    }

    @Test
    void getAllReviewsByUsername() throws Exception{

        // Arrange
        ReviewInsertDTO insertDTO1 = new ReviewInsertDTO();
        insertDTO1.setUsername("662a3");
        insertDTO1.setSubject("");
        insertDTO1.setDescription("");

        ReviewInsertDTO insertDTO2 = new ReviewInsertDTO();
        insertDTO2.setUsername("662a3");
        insertDTO2.setSubject("");
        insertDTO2.setDescription("");

        ReviewInsertDTO insertDTO3 = new ReviewInsertDTO();
        insertDTO3.setUsername("662a3");
        insertDTO3.setSubject("");
        insertDTO3.setDescription("");

        // Act
        Review insertedReview1 = reviewService.insertReview(insertDTO1);
        Review insertedReview2 = reviewService.insertReview(insertDTO2);
        Review insertedReview3 = reviewService.insertReview(insertDTO3);
        final List<Review> reviewList = reviewService.getAllReviewsByUsername("662a3");

        // Assert
        assertAll(
                () -> assertNotNull(reviewList),
                () -> assertNotEquals(0, reviewList.size()),
                () -> assertEquals(3, reviewList.size())
        );
    }

    @Test
    void getAllReviewsByTitle() throws Exception {
        // Arrange
        ReviewInsertDTO insertDTO1 = new ReviewInsertDTO();
        insertDTO1.setUsername("");
        insertDTO1.setSubject("First subject");
        insertDTO1.setDescription("");

        ReviewInsertDTO insertDTO2 = new ReviewInsertDTO();
        insertDTO2.setUsername("");
        insertDTO2.setSubject("Another interesting product");
        insertDTO2.setDescription("");

        ReviewInsertDTO insertDTO3 = new ReviewInsertDTO();
        insertDTO3.setUsername("");
        insertDTO3.setSubject("Third subject");
        insertDTO3.setDescription("");

        // Act
        Review insertedReview1 = reviewService.insertReview(insertDTO1);
        Review insertedReview2 = reviewService.insertReview(insertDTO2);
        Review insertedReview3 = reviewService.insertReview(insertDTO3);
        final List<Review> reviewList = reviewService.getAllReviewsBySubject("subject");

        // Assert
        assertAll(
                () -> assertNotNull(reviewList),
                () -> assertNotEquals(0, reviewList.size()),
                () -> assertEquals(2, reviewList.size())
        );
     }
}