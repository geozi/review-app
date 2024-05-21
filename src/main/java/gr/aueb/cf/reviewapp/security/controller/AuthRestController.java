package gr.aueb.cf.reviewapp.security.controller;

import gr.aueb.cf.reviewapp.security.model.*;
import gr.aueb.cf.reviewapp.security.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The {@link AuthRestController} class handles
 * user login requests.
 * @author geozi
 * @version 1
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;

    @Operation(summary = "Authenticate a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthGoodResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthBadResponse.class))}),
    })
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> authenticateUser(@Valid @RequestBody
                                                             AuthRequest authRequest) throws Exception {
        Authentication authentication;

        try {
            authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch(BadCredentialsException e) {
            return new ResponseEntity<>(new AuthBadResponse("Invalid input supplied"), HttpStatus.BAD_REQUEST);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        String jwt = jwtService.generateToken(authUser);

        return new ResponseEntity<>(new AuthGoodResponse(jwt), HttpStatus.OK);
    }
}
