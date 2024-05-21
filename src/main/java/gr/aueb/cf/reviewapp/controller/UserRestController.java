package gr.aueb.cf.reviewapp.controller;

import gr.aueb.cf.reviewapp.service.IUserService;
import gr.aueb.cf.reviewapp.service.dto.insertion.UserInsertDTO;
import gr.aueb.cf.reviewapp.service.dto.registration.UserRegDTO;
import gr.aueb.cf.reviewapp.service.validation.UserInsertValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@link UserRestController} class handles
 * the registration requests of new users.
 * @author geozi
 * @version 1
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserService userService;
    private final UserInsertValidator userInsertValidator;

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRegDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRegDTO.class))}),
            @ApiResponse(responseCode = "503", description = "Username already in use",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRegDTO.class))})
    })
    @PostMapping("/register")
    public ResponseEntity<UserRegDTO> registerUser(@Valid @RequestBody UserInsertDTO dto,
                                                   BindingResult bindingResult) {

        userInsertValidator.validate(dto, bindingResult);
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(new UserRegDTO("Invalid input provided"), HttpStatus.BAD_REQUEST);
        }

        try {
            userService.insertUser(dto);
            return new ResponseEntity<>(new UserRegDTO("User created"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new UserRegDTO("Username already in use"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
