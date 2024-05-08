package gr.aueb.cf.reviewapp.security.filter;

import gr.aueb.cf.reviewapp.security.model.AuthUser;
import gr.aueb.cf.reviewapp.security.service.JwtService;
import gr.aueb.cf.reviewapp.security.service.AuthUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * The {@link JwtControlFilter} class is a jwt-specific
 * implementation of the {@link OncePerRequestFilter} abstract
 * class.
 * @author geozi
 * version 1
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtControlFilter extends OncePerRequestFilter {

    private final AuthUserDetailService authUserDetailService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull  HttpServletRequest request,@NonNull HttpServletResponse response, @NonNull
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String jwt = jwtService.extractToken(request);
            if(!Objects.equals(jwt, null) && jwtService.validateToken(jwt)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {

                String username = jwtService.extractUsername(jwt);
                AuthUser authUser = (AuthUser) authUserDetailService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch(UsernameNotFoundException e) {
            log.info(e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
