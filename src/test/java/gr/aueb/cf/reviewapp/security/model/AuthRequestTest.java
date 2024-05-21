package gr.aueb.cf.reviewapp.security.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthRequestTest {

    private static AuthRequest authRequest;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {authRequest = new AuthRequest();}

    @BeforeEach
    void setUp() {
        authRequest.setUsername("user1");
        authRequest.setPassword("12345");
    }

    @AfterEach
    void tearDown() {
        authRequest.setUsername(null);
        authRequest.setPassword(null);
    }

    // Testing getters

    @Test
    void getUsername() {
        String username = authRequest.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertFalse(username.isEmpty()),
                () -> assertEquals("user1", username)
        );
    }

    @Test
    void getPassword() {
        String password = authRequest.getPassword();
        assertAll(
                () -> assertNotNull(password),
                () -> assertFalse(password.isEmpty()),
                () -> assertEquals("12345", password)
        );
    }

    // Testing setters

    @Test
    void setUsername() {
        authRequest.setUsername("user2");
        String username = authRequest.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertFalse(username.isEmpty()),
                () -> assertNotEquals("user1", username),
                () -> assertEquals("user2", username)
        );
    }

    @Test
    void setPassword() {
        authRequest.setPassword("TybRBQAnXu7U");
        String password = authRequest.getPassword();
        assertAll(
                () -> assertNotNull(password),
                () -> assertFalse(password.isEmpty()),
                () -> assertNotEquals("12345", password),
                () -> assertEquals("TybRBQAnXu7U", password)
        );
    }

}