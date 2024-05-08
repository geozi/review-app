package gr.aueb.cf.reviewapp.service;

import gr.aueb.cf.reviewapp.model.User;
import gr.aueb.cf.reviewapp.service.dto.insertion.UserInsertDTO;
import gr.aueb.cf.reviewapp.service.dto.update.UserUpdateDTO;
import gr.aueb.cf.reviewapp.service.exceptions.DocumentNotFoundException;

/**
 * The {@link IUserService} interface contains
 * the service-layer definitions of the CRUD methods
 * used for managing User documents.
 */
public interface IUserService {
    User insertUser(UserInsertDTO dto) throws Exception;
    User updateUser(UserUpdateDTO dto) throws DocumentNotFoundException;
    User deleteUser(String id) throws DocumentNotFoundException;
    User getUserById(String id) throws DocumentNotFoundException;
    User getUserByUsername(String username) throws DocumentNotFoundException;
}
