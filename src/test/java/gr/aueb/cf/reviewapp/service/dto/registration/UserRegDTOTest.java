package gr.aueb.cf.reviewapp.service.dto.registration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegDTOTest {
    private static UserRegDTO userRegDTO;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {userRegDTO = new UserRegDTO();}

    @BeforeEach
    void setUp() {
        userRegDTO.setMessage("User created");
    }

    @AfterEach
    void tearDown() {
        userRegDTO.setMessage(null);
    }

    // Testing getters

    @Test
    void getMessage() {
        String message = userRegDTO.getMessage();
        assertAll(
                () -> assertNotNull(message),
                () -> assertFalse(message.isEmpty()),
                () -> assertEquals("User created", message)
        );
    }

    // Testing setters

    @Test
    void setMessage() {
        userRegDTO.setMessage("User registration failure");
        String message = userRegDTO.getMessage();
        assertAll(
                () -> assertNotNull(message),
                () -> assertFalse(message.isEmpty()),
                () -> assertNotEquals("User created", message),
                () -> assertEquals("User registration failure", message)
        );
    }

}