package gr.aueb.cf.reviewapp.service.dto.update;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewUpdateDTOTest {
    private static ReviewUpdateDTO reviewDTO;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        reviewDTO = new ReviewUpdateDTO();
    }

    @BeforeEach
    void setUp() {
        reviewDTO.setId("662671f3adf94dcf69cac003");
        reviewDTO.setUsername("8b3365");
        reviewDTO.setSubject("Testing review subject");
        reviewDTO.setDescription("Testing review object");
    }

    @AfterEach
    void tearDown() {
        reviewDTO.setId(null);
        reviewDTO.setUsername(null);
        reviewDTO.setSubject(null);
        reviewDTO.setDescription(null);
    }

    // Testing getters

    @Test
    void getId() {
        String id = reviewDTO.getId();
        assertAll(
                () -> assertNotNull(id),
                () -> assertEquals("662671f3adf94dcf69cac003", id)
        );
    }

    @Test
    void getUserId() {
        String username = reviewDTO.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertEquals("8b3365", username)
        );
    }

    @Test
    void getSubject() {
        String subject = reviewDTO.getSubject();
        assertAll(
                () -> assertNotNull(subject),
                () -> assertFalse(subject.isEmpty()),
                () -> assertEquals("Testing review subject", subject)
        );
    }

    @Test
    void getDescription() {
        String description = reviewDTO.getDescription();
        assertAll(
                () -> assertNotNull(description),
                () -> assertFalse(description.isEmpty()),
                () -> assertEquals("Testing review object", description)
        );
    }

    // Testing setters

    @Test
    void setId() {
        reviewDTO.setId("6626732ecf2d745bda6ffcb8");
        String id = reviewDTO.getId();
        assertAll(
                () -> assertNotNull(id),
                () -> assertNotEquals("662671f3adf94dcf69cac003", id),
                () -> assertEquals("6626732ecf2d745bda6ffcb8", id)
        );
    }

    @Test
    void setUserId() {
        reviewDTO.setUsername("fd12c5");
        String username = reviewDTO.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertNotEquals("8b3365", username),
                () -> assertEquals("fd12c5", username)
        );
    }

    @Test
    void setSubject() {
        reviewDTO.setSubject("New Subject");
        String subject = reviewDTO.getSubject();
        assertAll(
                () -> assertNotNull(subject),
                () -> assertFalse(subject.isEmpty()),
                () -> assertNotEquals("Testing review subject", subject),
                () -> assertEquals("New Subject", subject)
        );
    }

    @Test
    void setDescription() {
        reviewDTO.setDescription("New description");
        String description = reviewDTO.getDescription();
        assertAll(
                () -> assertNotNull(description),
                () -> assertFalse(description.isEmpty()),
                () -> assertNotEquals("Testing review object", description),
                () -> assertEquals("New description", description)
        );
    }
}