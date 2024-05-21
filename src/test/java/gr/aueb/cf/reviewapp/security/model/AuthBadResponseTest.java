package gr.aueb.cf.reviewapp.security.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthBadResponseTest {

    private static AuthBadResponse authBadResponse;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {authBadResponse = new AuthBadResponse();}

    @BeforeEach
    void setUp() {
        authBadResponse.setMessage("Invalid input supplied");
    }

    @AfterEach
    void tearDown() {
        authBadResponse.setMessage(null);
    }

    // Testing getters

    @Test
    void getMessage() {
        String message = authBadResponse.getMessage();
        assertAll(
                () -> assertNotNull(message),
                () -> assertFalse(message.isEmpty()),
                () -> assertEquals("Invalid input supplied", message)
        );
    }

    // Testing setters

    @Test
    void setMessage() {
        authBadResponse.setMessage("Bad data");
        String message = authBadResponse.getMessage();
        assertAll(
                () -> assertNotNull(message),
                () -> assertFalse(message.isEmpty()),
                () -> assertNotEquals("Invalid input supplied", message),
                () -> assertEquals("Bad data", message)
        );
    }

}