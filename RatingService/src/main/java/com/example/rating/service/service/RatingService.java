package com.example.rating.service.service;

import com.example.rating.service.entity.Rating;

import java.util.List;

public interface RatingService {

    // create
    Rating create(Rating rating);

    // get all ratings
    List<Rating> getRatings();

    // get all by user id
    List<Rating> getRatingByUserId(String userId);

    // get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
