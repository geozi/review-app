package gr.aueb.cf.reviewapp.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    private static Review review;

    // Setting up test prep conditions

    @BeforeAll
    static void setUpTesting() {
        review = new Review();
    }

    @BeforeEach
    void setUp() {
        review.setId("662546ec558da49118339d15");
        review.setUsername("user1");
        review.setSubject("Service as expected");
        review.setDescription("Dish was delicious!");
    }

    @AfterEach
    void tearDown() {
        review.setId(null);
        review.setUsername(null);
        review.setSubject(null);
        review.setDescription(null);
    }

    // Testing getters

    @Test
    void getId() {
        String id = review.getId();
        assertAll(
                () -> assertNotNull(id),
                () -> assertEquals("662546ec558da49118339d15", id)
        );
    }

    @Test
    void getUsername() {
        String id = review.getUsername();
        assertAll(
                () -> assertNotNull(id),
                () -> assertEquals("user1", id)
        );
    }

    @Test
    void getSubject() {
        String subject = review.getSubject();
        assertAll(
                () -> assertNotNull(subject),
                () -> assertFalse(subject.isEmpty()),
                () -> assertEquals("Service as expected", subject)
        );
    }

    @Test
    void getDescription() {
        String description = review.getDescription();
        assertAll(
                () -> assertNotNull(description),
                () -> assertFalse(description.isEmpty()),
                () -> assertEquals("Dish was delicious!", description)
        );
    }

    // Testing setters

    @Test
    void setId() {
        review.setId("66254af0b9b18cd6bdadce62");
        String id = review.getId();
        assertAll(
                () -> assertNotNull(id),
                () -> assertNotEquals("662546ec558da49118339d15", id),
                () -> assertEquals("66254af0b9b18cd6bdadce62", id)
        );

    }

    @Test
    void setUsername() {
        review.setUsername("user2");
        String id = review.getUsername();
        assertAll(
                () -> assertNotNull(id),
                () -> assertNotEquals("user1", id),
                () -> assertEquals("user2", id)
        );
    }

    @Test
    void setSubject() {
        review.setSubject("Good pastry");
        String subject = review.getSubject();
        assertAll(
                () -> assertNotNull(subject),
                () -> assertFalse(subject.isEmpty()),
                () -> assertNotEquals("Service as expected", subject),
                () -> assertEquals("Good pastry", subject)
        );
    }

    @Test
    void setDescription() {
        review.setDescription("I really liked it!");
        String description = review.getDescription();
        assertAll(
                () -> assertNotNull(description),
                () -> assertFalse(description.isEmpty()),
                () -> assertNotEquals("Dish was delicious!", description),
                () -> assertEquals("I really liked it!", description)
        );
    }
}