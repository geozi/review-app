package gr.aueb.cf.reviewapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Backend implementation of a review app.
 * @author geozi
 * @version 1.0
 *
 */
@SpringBootApplication
@EnableMongoRepositories
public class ReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}

}
