package gr.aueb.cf.reviewapp.service.dto.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewCrudExceptionDTOTest {

    private static ReviewCrudExceptionDTO reviewCrudExceptionDTO;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting(){reviewCrudExceptionDTO = new ReviewCrudExceptionDTO();}

    @BeforeEach
    void setUp() {
        reviewCrudExceptionDTO.setMessage("Invalid input provided");
    }

    @AfterEach
    void tearDown() {
        reviewCrudExceptionDTO.setMessage(null);
    }

    // Testing getters

    @Test
    void getMessage() {
        String message = reviewCrudExceptionDTO.getMessage();
        assertAll(
                () -> assertNotNull(message),
                () -> assertFalse(message.isEmpty()),
                () -> assertEquals("Invalid input provided", message)
        );
    }

    // Testing setters

    @Test
    void setMessage() {
        reviewCrudExceptionDTO.setMessage("Review addition failed");
        String message = reviewCrudExceptionDTO.getMessage();
        assertAll(
                () -> assertNotNull(message),
                () -> assertFalse(message.isEmpty()),
                () -> assertNotEquals("Invalid input provided", message),
                () -> assertEquals("Review addition failed" , message)
        );
    }

}