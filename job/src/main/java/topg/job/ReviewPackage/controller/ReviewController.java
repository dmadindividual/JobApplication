package topg.job.ReviewPackage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import topg.job.ReviewPackage.model.Review;
import topg.job.ReviewPackage.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;


    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Integer companyId){
        return new ResponseEntity<>(reviewService.getAllReview(companyId),    HttpStatus.OK);


    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Integer companyId, @RequestBody Review review){
        reviewService.createReview(companyId, review);
        return new ResponseEntity<>("Review added succesfully", HttpStatus.CREATED);

    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Integer companyId, @PathVariable Integer reviewId ){
        Review review = reviewService.getReviewById(companyId, reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> editReviewById(@PathVariable Integer companyId, @PathVariable Integer reviewId, @RequestBody Review review) {
        try {
            reviewService.editReviewById(companyId, reviewId, review);
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Integer companyId, @PathVariable Integer reviewId) {
        try {
            reviewService.deleteReviewById(companyId, reviewId);
            return new ResponseEntity<>("Successfully deleted review", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
