package gr.aueb.cf.reviewapp.service.dto.insertion;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewInsertDTOTest {

    private static ReviewInsertDTO reviewInsertDTO;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        reviewInsertDTO = new ReviewInsertDTO();
    }

    @BeforeEach
    void setUp() {
        reviewInsertDTO.setUsername("user66255");
        reviewInsertDTO.setSubject("Another review");
        reviewInsertDTO.setDescription("This is another review");
    }

    @AfterEach
    void tearDown() {
        reviewInsertDTO.setUsername(null);
        reviewInsertDTO.setSubject(null);
        reviewInsertDTO.setDescription(null);
    }

    // Testing getters

    @Test
    void getUserId() {
        String username = reviewInsertDTO.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertEquals("user66255", username)
        );
    }

    @Test
    void getSubject() {
        String subject = reviewInsertDTO.getSubject();
        assertAll(
                () -> assertNotNull(subject),
                () -> assertFalse(subject.isEmpty()),
                () -> assertEquals("Another review", subject)
        );
    }

    @Test
    void getDescription() {
        String description = reviewInsertDTO.getDescription();
        assertAll(
                () -> assertNotNull(description),
                () -> assertFalse(description.isEmpty()),
                () -> assertEquals("This is another review", description)
        );
    }

    // Testing setters

    @Test
    void setUserId() {
        reviewInsertDTO.setUsername("userC29");
        String username = reviewInsertDTO.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertNotEquals("user66255", username),
                () -> assertEquals("userC29", username)
        );

    }

    @Test
    void setSubject() {
        reviewInsertDTO.setSubject("New subject");
        String subject = reviewInsertDTO.getSubject();
        assertAll(
                () -> assertNotNull(subject),
                () -> assertFalse(subject.isEmpty()),
                () -> assertNotEquals("Another review", subject),
                () -> assertEquals("New subject", subject)
        );

    }

    @Test
    void setDescription() {
        reviewInsertDTO.setDescription("This is a new description");
        String description = reviewInsertDTO.getDescription();
        assertAll(
                () -> assertNotNull(description),
                () -> assertFalse(description.isEmpty()),
                () -> assertNotEquals("This is another review", description),
                () -> assertEquals("This is a new description", description)
        );

    }
}