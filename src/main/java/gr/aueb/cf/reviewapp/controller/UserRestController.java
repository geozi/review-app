package gr.aueb.cf.reviewapp.controller;

import gr.aueb.cf.reviewapp.service.IUserService;
import gr.aueb.cf.reviewapp.service.dto.insertion.UserInsertDTO;
import gr.aueb.cf.reviewapp.service.validation.UserInsertValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@link UserRestController} class handles
 * the registration requests of new users.
 * @author geozi
 * @version 1
 */
@RestController
public class UserRestController {

    private final IUserService userService;
    private final UserInsertValidator userInsertValidator;

    @Autowired
    public UserRestController(IUserService userService, UserInsertValidator userInsertValidator) {
        this.userService = userService;
        this.userInsertValidator = userInsertValidator;
    }

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User added",
            content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input supplied", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service unavailable",
            content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<HttpStatusCode> registerUser(@Valid @RequestBody UserInsertDTO dto,
                                                       BindingResult bindingResult) {

        userInsertValidator.validate(dto, bindingResult);
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            userService.insertUser(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
