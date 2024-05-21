package gr.aueb.cf.reviewapp.security.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthGoodResponseTest {

    private static AuthGoodResponse authGoodResponse;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {authGoodResponse = new AuthGoodResponse();}

    @BeforeEach
    void setUp() {
        authGoodResponse.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
    }

    @AfterEach
    void tearDown() {
        authGoodResponse.setToken(null);
    }

    // Testing getters

    @Test
    void getToken() {
        String token = authGoodResponse.getToken();
        assertAll(
                () -> assertNotNull(token),
                () -> assertFalse(token.isEmpty()),
                () -> assertEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                        "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                        "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c", token)
        );
    }

    // Testing setters

    @Test
    void setToken() {
        authGoodResponse.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6InJhbmRvbSBuYW1lIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                "D-T7HQdI6yUsVKisOGkIfY1XLyK4kXBLkXxy3lQ3JZs");
        String token = authGoodResponse.getToken();
        assertAll(
                () -> assertNotNull(token),
                () -> assertFalse(token.isEmpty()),
                () -> assertNotEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                        "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                        "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c", token),
                () -> assertEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                        "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6InJhbmRvbSBuYW1lIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                        "D-T7HQdI6yUsVKisOGkIfY1XLyK4kXBLkXxy3lQ3JZs", token)
        );
    }
}