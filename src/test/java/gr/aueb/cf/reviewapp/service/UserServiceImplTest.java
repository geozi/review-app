package gr.aueb.cf.reviewapp.service;

import gr.aueb.cf.reviewapp.model.Role;
import gr.aueb.cf.reviewapp.model.User;
import gr.aueb.cf.reviewapp.repository.UserRepo;
import gr.aueb.cf.reviewapp.service.dto.insertion.UserInsertDTO;
import gr.aueb.cf.reviewapp.service.dto.update.UserUpdateDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    private final UserRepo repo;
    private final UserServiceImpl service;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplTest(UserRepo repo, UserServiceImpl service, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @AfterEach
    void tearDown() {
        repo.deleteAll();
    }

    @Test
    void insertUser() throws Exception {

        // Arrange
        UserInsertDTO dto = new UserInsertDTO();
        dto.setUsername("logger");
        dto.setPassword("LgLGnwvBcb");
        dto.setEmail("test@test.com");
        dto.setRole(Role.USER);

        // Act
        User insertedUser = service.insertUser(dto);

        // Assert
        assertAll(
                () -> assertNotNull(insertedUser),
                () -> assertNotNull(insertedUser.getId()),
                () -> assertNotNull(insertedUser.getUsername()),
                () -> assertNotNull(insertedUser.getPassword()),
                () -> assertNotNull(insertedUser.getEmail()),
                () -> assertNotNull(insertedUser.getRole()),
                () -> assertFalse(insertedUser.getId().isEmpty()),
                () -> assertFalse(insertedUser.getUsername().isEmpty()),
                () -> assertFalse(insertedUser.getPassword().isEmpty()),
                () -> assertFalse(insertedUser.getEmail().isEmpty()),
                () -> assertEquals("logger", insertedUser.getUsername()),
                () -> assertEquals(60, insertedUser.getPassword().length()),
                () -> assertTrue(passwordEncoder.matches("LgLGnwvBcb", insertedUser.getPassword())),
                () -> assertEquals("test@test.com", insertedUser.getEmail()),
                () -> assertEquals(Role.USER, insertedUser.getRole()));

    }

    @Test
    void updateUser() throws Exception {

        // Arrange
        UserInsertDTO insertDTO = new UserInsertDTO();
        insertDTO.setUsername("ch0r");
        insertDTO.setPassword("ml4cYHi9yu");
        insertDTO.setEmail("test@test.com");
        insertDTO.setRole(Role.USER);

        // Act
        User insertedUser = service.insertUser(insertDTO);

        // Arrange
        UserUpdateDTO updateDTO = new UserUpdateDTO();
        updateDTO.setId(insertedUser.getId());
        updateDTO.setUsername("helloWorld");
        updateDTO.setPassword("ml4cYHi9yu");
        updateDTO.setEmail("updated@email.com");
        updateDTO.setRole(Role.ADMIN);

        // Act
        User updatedUser = service.updateUser(updateDTO);

        // Assert
        assertAll(
                () -> assertNotNull(updatedUser),
                () -> assertNotNull(updatedUser.getId()),
                () -> assertNotNull(updatedUser.getUsername()),
                () -> assertNotNull(updatedUser.getPassword()),
                () -> assertNotNull(updatedUser.getEmail()),
                () -> assertNotNull(updatedUser.getRole()),
                () -> assertFalse(updatedUser.getId().isEmpty()),
                () -> assertFalse(updatedUser.getUsername().isEmpty()),
                () -> assertFalse(updatedUser.getPassword().isEmpty()),
                () -> assertFalse(updatedUser.getEmail().isEmpty()),
                () -> assertEquals(60, updatedUser.getPassword().length()),
                () -> assertEquals("helloWorld", updatedUser.getUsername()),
                () -> assertTrue(passwordEncoder.matches("ml4cYHi9yu", updatedUser.getPassword())),
                () -> assertEquals("updated@email.com", updatedUser.getEmail()),
                () -> assertEquals(Role.ADMIN, updatedUser.getRole())
        );

    }

    @Test
    void deleteUser() throws Exception {

        // Arrange
        UserInsertDTO insertDTO = new UserInsertDTO();
        insertDTO.setUsername("n@me");
        insertDTO.setPassword("NFqOTxZtNR");

        // Act
        User insertedUser = service.insertUser(insertDTO);
        User deletedUser = service.deleteUser(insertedUser.getId());

        // Assert
        assertNotNull(deletedUser);

    }

    @Test
    void getUserById() throws Exception {

        // Arrange
        UserInsertDTO insertDTO = new UserInsertDTO();
        insertDTO.setUsername("sky");
        insertDTO.setPassword("TjkseTtQAW");
        insertDTO.setEmail("mail@example.com");
        insertDTO.setRole(Role.USER);

        // Act
        User insertedUser = service.insertUser(insertDTO);
        User user = service.getUserById(insertedUser.getId());

        // Assert
        assertAll(
                () -> assertNotNull(user),
                () -> assertNotNull(user.getId()),
                () -> assertNotNull(user.getUsername()),
                () -> assertNotNull(user.getPassword()),
                () -> assertNotNull(user.getEmail()),
                () -> assertNotNull(user.getRole()),
                () -> assertFalse(user.getId().isEmpty()),
                () -> assertFalse(user.getUsername().isEmpty()),
                () -> assertFalse(user.getPassword().isEmpty()),
                () -> assertFalse(user.getEmail().isEmpty()),
                () -> assertEquals(60, user.getPassword().length()),
                () -> assertEquals("sky", user.getUsername()),
                () -> assertTrue(passwordEncoder.matches("TjkseTtQAW", user.getPassword())),
                () -> assertEquals("mail@example.com", user.getEmail()),
                () -> assertEquals(Role.USER, user.getRole())
        );
    }

    @Test
    void getUserByUsername() throws Exception {

        // Arrange
        UserInsertDTO insertDTO = new UserInsertDTO();
        insertDTO.setUsername("d0rian");
        insertDTO.setPassword("DmOePNuMj1");
        insertDTO.setEmail("random@mail.com");
        insertDTO.setRole(Role.USER);

        // Act
        User insertedUser = service.insertUser(insertDTO);
        User user = service.getUserByUsername(insertedUser.getUsername());

        // Assert
        assertAll(
                () -> assertNotNull(user),
                () -> assertNotNull(user.getId()),
                () -> assertNotNull(user.getUsername()),
                () -> assertNotNull(user.getPassword()),
                () -> assertNotNull(user.getEmail()),
                () -> assertNotNull(user.getRole()),
                () -> assertFalse(user.getId().isEmpty()),
                () -> assertFalse(user.getUsername().isEmpty()),
                () -> assertFalse(user.getPassword().isEmpty()),
                () -> assertFalse(user.getEmail().isEmpty()),
                () -> assertEquals(60, user.getPassword().length()),
                () -> assertEquals("d0rian", user.getUsername()),
                () -> assertTrue(passwordEncoder.matches("DmOePNuMj1", user.getPassword())),
                () -> assertEquals("random@mail.com", user.getEmail()),
                () -> assertEquals(Role.USER, user.getRole())
        );

    }

}