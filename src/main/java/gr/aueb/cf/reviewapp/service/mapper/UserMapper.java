package gr.aueb.cf.reviewapp.service.mapper;

import gr.aueb.cf.reviewapp.model.User;
import gr.aueb.cf.reviewapp.service.dto.insertion.UserInsertDTO;
import gr.aueb.cf.reviewapp.service.dto.readonly.UserReadOnlyDTO;
import gr.aueb.cf.reviewapp.service.dto.update.UserUpdateDTO;
import org.springframework.stereotype.Component;

/**
 * The {@link UserMapper} class is a utility class
 * that maps DTOs to the User domain model class and vice versa.
 * @author geozi
 * @version 1
 */
@Component
public class UserMapper {

    private UserMapper() {}

    public static User mapToUser(UserInsertDTO dto) {
        return new User(dto.getUsername(), dto.getPassword(), dto.getEmail(), dto.getRole());
    }

    public static User mapToUser(UserUpdateDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getEmail(), dto.getRole());
    }

    public static User mapToUser(UserReadOnlyDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword(),  dto.getEmail(), dto.getRole());
    }

    public static UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRole());
    }
}
