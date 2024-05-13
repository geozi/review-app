package gr.aueb.cf.reviewapp.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private static User user;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        user = new User();
    }

    @BeforeEach
    void setUp() {
        user.setId("6625440c62dd257d31054d38");
        user.setUsername("user1");
        user.setPassword("xo26geA9vn");
        user.setEmail("test@random.org");
        user.setRole(Role.USER);
    }

    @AfterEach
    void tearDown() {
        user.setId(null);
        user.setUsername(null);
        user.setPassword(null);
        user.setEmail(null);
        user.setRole(null);
    }

    // Testing getters

    @Test
    void getId() {
        Object id = user.getId();
        assertAll(
                () -> assertNotNull(id),
                () -> assertEquals("6625440c62dd257d31054d38", id.toString())
        );
    }

    @Test
    void getUsername() {
        String username = user.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertFalse(username.isEmpty()),
                () -> assertEquals("user1", username)
        );
    }

    @Test
    void getPassword() {
        String password = user.getPassword();
        assertAll(
                () -> assertNotNull(password),
                () -> assertFalse(password.isEmpty()),
                () -> assertEquals("xo26geA9vn", password)
        );
    }

    @Test
    void getEmail() {
        String email = user.getEmail();
        assertAll(
                () -> assertNotNull(email),
                () -> assertFalse(email.isEmpty()),
                () -> assertEquals("test@random.org", email)
        );
    }

    @Test
    void getRole() {
        Role role = user.getRole();
        assertAll(
                () -> assertNotNull(role),
                () -> assertEquals(Role.USER, role)
        );
    }

    // Testing setters

    @Test
    void setId() {
        user.setId("66254515c16a6ddd032c21f6");
        String id = user.getId();
        assertAll(
                () -> assertNotNull(id),
                () -> assertNotEquals("6625440c62dd257d31054d38", id),
                () -> assertEquals("66254515c16a6ddd032c21f6", id)
        );
    }

    @Test
    void setUsername() {
        user.setUsername("user2");
        String username = user.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertFalse(username.isEmpty()),
                () -> assertNotEquals("user1", username),
                () -> assertEquals("user2", username)
        );
    }

    @Test
    void setPassword() {
        user.setPassword("RbRXP5fn7Q");
        String password = user.getPassword();
        assertAll(
                () -> assertNotNull(password),
                () -> assertFalse(password.isEmpty()),
                () -> assertNotEquals("xo26geA9vn", password),
                () -> assertEquals("RbRXP5fn7Q", password)
        );
    }

    @Test
    void setEmail() {
        user.setEmail("newEmail@email.org");
        String email = user.getEmail();
        assertAll(
                () -> assertNotNull(email),
                () -> assertFalse(email.isEmpty()),
                () -> assertNotEquals("test@random.org", email),
                () -> assertEquals("newEmail@email.org", email)
        );
    }

    @Test
    void setRole() {
        user.setRole(Role.ADMIN);
        Role role = user.getRole();
        assertAll(
                () -> assertNotNull(role),
                () -> assertNotEquals(Role.USER, role),
                () -> assertEquals(Role.ADMIN, role)
        );
    }
}