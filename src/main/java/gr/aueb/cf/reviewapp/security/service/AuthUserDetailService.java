package gr.aueb.cf.reviewapp.security.service;

import gr.aueb.cf.reviewapp.model.User;
import gr.aueb.cf.reviewapp.repository.UserRepo;
import gr.aueb.cf.reviewapp.security.model.AuthUser;
import gr.aueb.cf.reviewapp.service.exceptions.DocumentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The {@link AuthUserDetailService} class implements the
 * {@link UserDetailsService} interface.
 * @author geozi
 * @version 1
 */
@Service
@RequiredArgsConstructor
public class AuthUserDetailService implements UserDetailsService {

    private final UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser;
        AuthUser authUser;

        try {
            optionalUser = repo.findByUsername(username);
            if(optionalUser.isEmpty()) throw new DocumentNotFoundException(User.class, username);
            authUser = new AuthUser(optionalUser.get());

        } catch(DocumentNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

        return authUser;
    }
}
