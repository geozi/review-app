package gr.aueb.cf.reviewapp.service;

import com.mongodb.DuplicateKeyException;
import gr.aueb.cf.reviewapp.model.User;
import gr.aueb.cf.reviewapp.repository.UserRepo;
import gr.aueb.cf.reviewapp.service.dto.insertion.UserInsertDTO;
import gr.aueb.cf.reviewapp.service.dto.update.UserUpdateDTO;
import gr.aueb.cf.reviewapp.service.exceptions.DocumentNotFoundException;
import gr.aueb.cf.reviewapp.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * The {@link UserServiceImpl} class implements
 * the CRUD wrapper methods as defined in the
 * {@link IUserService} interface.
 * @author geozi
 * @version 1
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService{

    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * Inserts a new {@link User} record.
     * @param dto The {@link UserInsertDTO} containing the information of the new {@link User}.
     * @return The newly created {@link User} record as an object.
     * @throws Exception Is thrown when an error is found during the insertion operation.
     */
    @Override
    @Transactional
    public User insertUser(UserInsertDTO dto) throws Exception {
        User user;
        String clearTextPassword;

        try {
            clearTextPassword = dto.getPassword();
            dto.setPassword(passwordEncoder.encode(clearTextPassword));
            user = repo.save(UserMapper.mapToUser(dto));
            if(Objects.equals(user.getId(),null)) throw new Exception("Insertion error");
        } catch(DuplicateKeyException e) {
            log.info("Duplicate entry error");
            throw e;
        } catch(Exception e) {
            log.info("Insertion error");
            throw e;
        }
        return user;
    }

    /**
     * Updates an existing {@link User} record.
     * @param dto The {@link UserUpdateDTO} containing the updated information of the {@link User}.
     * @return The updated {@link User} record as an object.
     * @throws DocumentNotFoundException Is thrown when the {@link User} record to be updated is not found.
     */
    @Override
    @Transactional
    public User updateUser(UserUpdateDTO dto) throws DocumentNotFoundException {
        Optional<User> optionalUser;
        User updatedUser;
        String clearTextPassword;

        try {
            optionalUser = repo.findById(dto.getId());
            if(optionalUser.isEmpty()) throw new DocumentNotFoundException(User.class, dto.getId());
            clearTextPassword = dto.getPassword();
            dto.setPassword(passwordEncoder.encode(clearTextPassword));
            updatedUser = repo.save(UserMapper.mapToUser(dto));
        } catch (DocumentNotFoundException e) {
            log.info("Update error");
            throw e;
        }

        return updatedUser;
    }

    /**
     * Deletes an existing {@link User} record.
     * @param id The id of the {@link User} record to be deleted.
     * @return The deleted {@link User} record as an object.
     * @throws DocumentNotFoundException Is thrown when the {@link User} record to be deleted is not found.
     */
    @Override
    @Transactional
    public User deleteUser(String id) throws DocumentNotFoundException {
        Optional<User> optionalUser;
        User deletedUser;

        try {
            optionalUser = repo.findById(id);
            if(optionalUser.isEmpty()) throw new DocumentNotFoundException(User.class, id);
            deletedUser = optionalUser.get();
            repo.delete(deletedUser);
        } catch (DocumentNotFoundException e) {
            log.info("Deletion error");
            throw e;
        }
        return deletedUser;
    }

    /**
     * Retrieves a {@link User} record by id.
     * @param id The id of the {@link User} record to be retrieved.
     * @return The retrieved {@link User} record as an object.
     * @throws DocumentNotFoundException Is thrown when the {@link User} record to be retrieved is not found.
     */
    @Override
    public User getUserById(String id) throws DocumentNotFoundException {
        Optional<User> optionalUser;
        User user;

        try {
            optionalUser = repo.findById(id);
            if(optionalUser.isEmpty()) throw new DocumentNotFoundException(User.class, id);
            user = optionalUser.get();
        } catch(DocumentNotFoundException e) {
            log.info("Retrieval by id error");
            throw e;
        }

        return user;
    }

    /**
     * Retrieves a {@link User} record by username.
     * @param username The username of the {@link User} record to be retrieved.
     * @return The retrieved {@link User} record as an object.
     * @throws DocumentNotFoundException Is thrown when the {@link User} record to be retrieved is not found.
     */
    @Override
    public User getUserByUsername(String username) throws DocumentNotFoundException {
        Optional<User> optionalUser;
        User user;

        try {
            optionalUser = repo.findByUsername(username);
            if(optionalUser.isEmpty()) throw new DocumentNotFoundException(User.class, username);
            user = optionalUser.get();
        } catch(DocumentNotFoundException e) {
            log.info("Retrieval by username error");
            throw e;
        }

        return user;
    }
}
