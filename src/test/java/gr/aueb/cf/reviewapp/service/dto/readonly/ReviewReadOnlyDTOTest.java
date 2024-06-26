package gr.aueb.cf.reviewapp.service.dto.readonly;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ReviewReadOnlyDTOTest {

    private static ReviewReadOnlyDTO reviewDTO;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        reviewDTO = new ReviewReadOnlyDTO();
    }

    @BeforeEach
    void setUp() {
        reviewDTO.setId("662671f3adf94dcf69cac003");
        reviewDTO.setUsername("ad8b");
        reviewDTO.setSubject("Testing review subject");
        reviewDTO.setDescription("Testing review object");

        String date = "2024-06-12T13:17:32.235Z";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        reviewDTO.setCreated(localDateTime);
    }

    @AfterEach
    void tearDown() {
        reviewDTO.setId(null);
        reviewDTO.setUsername(null);
        reviewDTO.setSubject(null);
        reviewDTO.setDescription(null);
        reviewDTO.setCreated(null);
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
    void getUsername() {
        String username = reviewDTO.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertEquals("ad8b", username)
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

    @Test
    void getCreated() {
        LocalDateTime localDateTime = reviewDTO.getCreated();
        assertAll(
                () -> assertNotNull(localDateTime),
                () -> assertEquals("2024-06-12T13:17:32.235", localDateTime.toString())
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
        reviewDTO.setUsername("eb2210");
        String username = reviewDTO.getUsername();
        assertAll(
                () -> assertNotNull(username),
                () -> assertNotEquals("ad8b", username),
                () -> assertEquals("eb2210", username)
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

    @Test
    void setCreated(){

        String date = "2024-06-12T12:53:39.242Z";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime addedLocalDateTime = LocalDateTime.parse(date, formatter);
        reviewDTO.setCreated(addedLocalDateTime);

        LocalDateTime retrievedLocalDateTime = reviewDTO.getCreated();
        assertAll(
                () -> assertNotNull(retrievedLocalDateTime),
                () -> assertNotEquals("2024-06-12T13:17:32.235", retrievedLocalDateTime.toString()),
                () -> assertEquals("2024-06-12T12:53:39.242", retrievedLocalDateTime.toString())
        );
    }
}