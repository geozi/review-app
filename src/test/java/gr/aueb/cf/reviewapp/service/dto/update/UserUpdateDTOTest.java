package gr.aueb.cf.reviewapp.service.dto.update;

import gr.aueb.cf.reviewapp.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserUpdateDTOTest {

    private static UserUpdateDTO userDTO;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        userDTO = new UserUpdateDTO();
    }

    @BeforeEach
    void setUp() {
        userDTO.setId("662674b67d15975b73a50be2");
        userDTO.setUsername("th0rax*");
        userDTO.setPassword("IiKU1xl3t4");
        userDTO.setRole(Role.USER);
    }

    @AfterEach
    void tearDown() {
        userDTO.setId(null);
        userDTO.setUsername(null);
        userDTO.setPassword(null);
        userDTO.setRole(null);
    }

    // Testing getters

    @Test
    void getId() {
        String id = userDTO.getId();
        assertAll(
                () -> assertNotNull(id),
                () -> assertEquals("662674b67d15975b73a50be2", id)
        );
    }

    @Test
    void getUsername() {
        String username = userDTO.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertFalse(username.isEmpty()),
                () -> assertEquals("th0rax*", username)
        );
    }

    @Test
    void getPassword() {
        String password = userDTO.getPassword();
        assertAll(
                () -> assertNotNull(password),
                () -> assertFalse(password.isEmpty()),
                () -> assertEquals("IiKU1xl3t4", password)
        );
    }

    @Test
    void getRole() {
        Role role = userDTO.getRole();
        assertAll(
                () -> assertNotNull(role),
                () -> assertEquals(Role.USER, role)
        );
    }

    // Testing setters

    @Test
    void setId() {
        userDTO.setId("66267ed39769fdbe6a5f56ee");
        String id = userDTO.getId();
        assertAll(
                () -> assertNotNull(id),
                () -> assertNotEquals("662674b67d15975b73a50be2", id),
                () -> assertEquals("66267ed39769fdbe6a5f56ee", id)
        );
    }

    @Test
    void setUsername() {
        userDTO.setUsername("NweJI");
        String username = userDTO.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertFalse(username.isEmpty()),
                () -> assertNotEquals("th0rax*", username),
                () -> assertEquals("NweJI", username)
        );
    }

    @Test
    void setPassword() {
        userDTO.setPassword("bjcmhAk0t4");
        String password = userDTO.getPassword();
        assertAll(
                () -> assertNotNull(password),
                () -> assertFalse(password.isEmpty()),
                () -> assertNotEquals("IiKU1xl3t4", password),
                () -> assertEquals("bjcmhAk0t4", password)
        );
    }

    @Test
    void setRole() {
        userDTO.setRole(Role.ADMIN);
        Role role = userDTO.getRole();
        assertAll(
                () -> assertNotNull(role),
                () -> assertNotEquals(Role.USER, role),
                () -> assertEquals(Role.ADMIN, role)
        );
    }

}