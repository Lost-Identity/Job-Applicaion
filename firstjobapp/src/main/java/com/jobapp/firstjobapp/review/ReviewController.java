package com.jobapp.firstjobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService service){
        this.reviewService = service;
    }

    //get all reviews
    @GetMapping(path = "/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){

        List<Review> reviews = reviewService.getAllReviews(companyId);
        if(reviews.isEmpty())
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    //get review by id
    @GetMapping(path = "/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){

        Review review = reviewService.getReviewById(companyId, reviewId);
        if(review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(new Review(), HttpStatus.NOT_FOUND);
    }

    // create a review
    @PostMapping(path = "/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review){

        boolean isReviewSaved = reviewService.createReview(companyId, review);
        if(isReviewSaved)
            return new ResponseEntity<>("Review Created Successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("Review not saved", HttpStatus.NOT_FOUND);
    }

    // update a review
    @PutMapping(path ="/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review){

        boolean updated = reviewService.updateReview(companyId, reviewId, review);
        if(updated)
            return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);
        return new ResponseEntity<>("Review not Found", HttpStatus.NOT_FOUND);
    }

    // delete a review by id
    @DeleteMapping(path = "/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){

        boolean deleted = reviewService.deleteReview(companyId, reviewId);
        if(deleted)
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        return new ResponseEntity<>("Review not Found", HttpStatus.NOT_FOUND);
    }
}
