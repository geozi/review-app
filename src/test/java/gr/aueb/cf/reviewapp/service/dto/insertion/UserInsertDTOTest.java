package gr.aueb.cf.reviewapp.service.dto.insertion;

import gr.aueb.cf.reviewapp.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInsertDTOTest {

    private static UserInsertDTO userInsertDTO;

    @BeforeAll
    static void setUpTesting() {
        userInsertDTO = new UserInsertDTO();
    }

    @BeforeEach
    void setUp() {
        userInsertDTO.setUsername("N0ev");
        userInsertDTO.setPassword("SFv4anEdam");
        userInsertDTO.setEmail("test@test.com");
        userInsertDTO.setRole(Role.USER);
    }

    @AfterEach
    void tearDown() {
        userInsertDTO.setUsername(null);
        userInsertDTO.setPassword(null);
        userInsertDTO.setEmail(null);
        userInsertDTO.setRole(null);
    }

    // Testing getters

    @Test
    void getUsername() {
        String username = userInsertDTO.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertFalse(username.isEmpty()),
                () -> assertEquals("N0ev", username)
        );
    }

    @Test
    void getPassword() {
        String password = userInsertDTO.getPassword();
        assertAll(
                () -> assertNotNull(password),
                () -> assertFalse(password.isEmpty()),
                () -> assertEquals("SFv4anEdam", password)
        );
    }

    @Test
    void getEmail() {
        String email = userInsertDTO.getEmail();
        assertAll(
                () -> assertNotNull(email),
                () -> assertFalse(email.isEmpty()),
                () -> assertEquals("test@test.com", email)
        );
    }

    @Test
    void getRole() {
        Role role = userInsertDTO.getRole();
        assertAll(
                () -> assertNotNull(role),
                () -> assertEquals(Role.USER, role)
        );
    }

    // Testing setters

    @Test
    void setUsername() {
        userInsertDTO.setUsername("user3");
        String username = userInsertDTO.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertFalse(username.isEmpty()),
                () -> assertNotEquals("N0ev", username),
                () -> assertEquals("user3", username)
        );
    }

    @Test
    void setPassword() {
        userInsertDTO.setPassword("WKm4NAMQLn");
        String password = userInsertDTO.getPassword();
        assertAll(
                () -> assertNotNull(password),
                () -> assertFalse(password.isEmpty()),
                () -> assertNotEquals("SFv4anEdam", password),
                () -> assertEquals("WKm4NAMQLn", password)
        );
    }

    @Test
    void setEmail() {
        userInsertDTO.setEmail("new@email.org");
        String email = userInsertDTO.getEmail();
        assertAll(
                () -> assertNotNull(email),
                () -> assertFalse(email.isEmpty()),
                () -> assertNotEquals("test@test.com", email),
                () -> assertEquals("new@email.org", email)
        );
    }

    @Test
    void setRole() {
        userInsertDTO.setRole(Role.ADMIN);
        Role role = userInsertDTO.getRole();
        assertAll(
                () -> assertNotNull(role),
                () -> assertNotEquals(Role.USER, role),
                () -> assertEquals(Role.ADMIN, role)
        );
    }
}