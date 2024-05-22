package gr.aueb.cf.reviewapp.controller;

import gr.aueb.cf.reviewapp.model.Review;
import gr.aueb.cf.reviewapp.service.IReviewService;
import gr.aueb.cf.reviewapp.service.dto.BaseDTO;
import gr.aueb.cf.reviewapp.service.dto.exception.ReviewCrudExceptionDTO;
import gr.aueb.cf.reviewapp.service.dto.insertion.ReviewInsertDTO;
import gr.aueb.cf.reviewapp.service.dto.readonly.ReviewReadOnlyDTO;
import gr.aueb.cf.reviewapp.service.dto.update.ReviewUpdateDTO;
import gr.aueb.cf.reviewapp.service.exceptions.DocumentNotFoundException;
import gr.aueb.cf.reviewapp.service.mapper.ReviewMapper;
import gr.aueb.cf.reviewapp.service.validation.ReviewInsertValidator;
import gr.aueb.cf.reviewapp.service.validation.ReviewUpdateValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

/**
 * The {@link ReviewRestController} class handles the
 * CRUD requests of Reviews.
 * @author geozi
 * @version 1
 */
@RestController
@RequestMapping("/api")
public class ReviewRestController {

    private final IReviewService reviewService;
    private final ReviewInsertValidator reviewInsertValidator;
    private final ReviewUpdateValidator reviewUpdateValidator;

    @Autowired
    public ReviewRestController(IReviewService reviewService, ReviewInsertValidator reviewInsertValidator,
                                ReviewUpdateValidator reviewUpdateValidator) {
        this.reviewService = reviewService;
        this.reviewInsertValidator = reviewInsertValidator;
        this.reviewUpdateValidator = reviewUpdateValidator;
    }

    @Operation(summary = "Get reviews by subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ReviewReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid subject supplied",
            content = @Content)
    })
    @GetMapping("/reviews-by-subject")
    public ResponseEntity<List<ReviewReadOnlyDTO>> getReviewsBySubject(@RequestParam("subject") String subject) {
        List<Review> reviews;

        try {
            reviews = reviewService.getAllReviewsBySubject(subject);
            List<ReviewReadOnlyDTO> reviewReadOnlyDTOS = new ArrayList<>();
            for(Review review : reviews) {
                reviewReadOnlyDTOS.add(ReviewMapper.mapToReviewReadOnlyDTO(review));
            }
            return new ResponseEntity<>(reviewReadOnlyDTOS, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get reviews by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ReviewReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied",
            content = @Content)
    })
    @GetMapping("/reviews-by-username")
    public ResponseEntity<List<ReviewReadOnlyDTO>> getReviewsByUsername(@RequestParam("username") String username) {
        List<Review> reviews;

        try {
            reviews = reviewService.getAllReviewsByUsername(username);
            List<ReviewReadOnlyDTO> reviewReadOnlyDTOS = new ArrayList<>();
            for (Review review : reviews) {
                reviewReadOnlyDTOS.add(ReviewMapper.mapToReviewReadOnlyDTO(review));
            }
            return new ResponseEntity<>(reviewReadOnlyDTOS, HttpStatus.OK);
        } catch (DocumentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Add a new review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review added",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ReviewReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input supplied",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ReviewCrudExceptionDTO.class))}),
            @ApiResponse(responseCode = "503", description = "Service unavailable",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ReviewCrudExceptionDTO.class))})
    })
    @PostMapping("/reviews")
    public ResponseEntity<BaseDTO> addReview(@Valid @RequestBody ReviewInsertDTO dto,
                                             BindingResult bindingResult) {
        reviewInsertValidator.validate(dto, bindingResult);

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ReviewCrudExceptionDTO("Bad data") ,HttpStatus.BAD_REQUEST);
        }

        try {
            Review review = reviewService.insertReview(dto);
            ReviewReadOnlyDTO reviewReadOnlyDTO = ReviewMapper.mapToReviewReadOnlyDTO(review);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(reviewReadOnlyDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).body(reviewReadOnlyDTO);
        } catch(Exception e) {
            return new ResponseEntity<>(new ReviewCrudExceptionDTO("Review addition failure"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Operation(summary = "Update a review by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review updated",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ReviewReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ReviewCrudExceptionDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ReviewCrudExceptionDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Review not found",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ReviewCrudExceptionDTO.class))})
    })
    @PutMapping("/reviews/{id}")
    public ResponseEntity<BaseDTO> updateReview(@PathVariable("id") String id,
                                                          @Valid @RequestBody ReviewUpdateDTO dto,
                                                          BindingResult bindingResult) {
        if(!Objects.equals(id, dto.getId())) return new ResponseEntity<>(new ReviewCrudExceptionDTO("Unauthorized user"),
                HttpStatus.UNAUTHORIZED);
        reviewUpdateValidator.validate(dto,bindingResult);
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ReviewCrudExceptionDTO("Invalid input provided"),HttpStatus.BAD_REQUEST);
        }

        try {
            Review review = reviewService.updateReview(dto);
            ReviewReadOnlyDTO reviewReadOnlyDTO = ReviewMapper.mapToReviewReadOnlyDTO(review);
            return new ResponseEntity<>(reviewReadOnlyDTO, HttpStatus.OK);
        } catch (DocumentNotFoundException e) {
            return new ResponseEntity<>(new ReviewCrudExceptionDTO("Review not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a review by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review deleted",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ReviewReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Review not found",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ReviewCrudExceptionDTO.class))})
    })
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<BaseDTO> deleteReview(@PathVariable("id") String id) {
        try {
            Review review = reviewService.getReviewById(id);
            reviewService.deleteReview(id);
            ReviewReadOnlyDTO reviewReadOnlyDTO = ReviewMapper.mapToReviewReadOnlyDTO(review);
            return new ResponseEntity<>(reviewReadOnlyDTO, HttpStatus.OK);
        } catch (DocumentNotFoundException e) {
            return new ResponseEntity<>(new ReviewCrudExceptionDTO("Review not found"),HttpStatus.NOT_FOUND);
        }
    }
}
