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

    @Override
    public User getUserById(String id) throws DocumentNotFoundException {
        Optional<User> optionalUser;
        User user;

        try {
            optionalUser = repo.findById(id);
            if(optionalUser.isEmpty()) throw new DocumentNotFoundException(User.class, id);
            user = optionalUser.get();
        } catch(DocumentNotFoundException e) {
            log.info("Retrieval error");
            throw e;
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username) throws DocumentNotFoundException {
        Optional<User> optionalUser;
        User user;

        try {
            optionalUser = repo.findByUsername(username);
            if(optionalUser.isEmpty()) throw new DocumentNotFoundException(User.class, username);
            user = optionalUser.get();
        } catch(DocumentNotFoundException e) {
            log.info("Retrieval error");
            throw e;
        }

        return user;
    }
}
