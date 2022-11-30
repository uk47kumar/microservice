package com.example.rating.service.repository;

import com.example.rating.service.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepo extends MongoRepository<Rating,String> {
}
