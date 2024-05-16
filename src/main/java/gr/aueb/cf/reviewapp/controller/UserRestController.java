package gr.aueb.cf.reviewapp.controller;

import gr.aueb.cf.reviewapp.service.IUserService;
import gr.aueb.cf.reviewapp.service.dto.insertion.UserInsertDTO;
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
            @ApiResponse(responseCode = "201", description = "User added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HashMap.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HashMap.class))}),
            @ApiResponse(responseCode = "503", description = "Service unavailable",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HashMap.class))})
    })
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@Valid @RequestBody UserInsertDTO dto,
                                                BindingResult bindingResult) {

        Map<String, String> body = new HashMap<>();

        userInsertValidator.validate(dto, bindingResult);
        if(bindingResult.hasErrors()) {
            body.put("message", "Invalid form data");
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        try {
            userService.insertUser(dto);
            body.put("message", "User created");
            return new ResponseEntity<>(body, HttpStatus.CREATED);
        } catch (Exception e) {
            body.put("message", "Username already in use");
            return new ResponseEntity<>(body, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
