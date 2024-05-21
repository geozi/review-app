package gr.aueb.cf.reviewapp.repository;

import gr.aueb.cf.reviewapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing Users.
 * Provides CRUD operations for working with User documents.
 * @author geozi
 * @version 1
 */
@Repository
public interface UserRepo extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);
}
