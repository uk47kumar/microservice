package com.user.service.service.impl;

import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.repository.UserRepo;
import com.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User saveUser(User user) {
        // generate unique userId
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {

//        get user from database with the help of userRepository
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User on given id is not found on server !! : " + userId));

//        to call through rating api that is (rating/user/userId)
//        fetch rating of the above user from the rating service
//        http://localhost:8083/rating/user/0f58e2d1-751b-4085-b62e-af0a97517812

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+ user.getUserId(), Rating[].class);
        logger.info("{} ",ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());

        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get the hotel
            // http://localhost:8082/hotel/38d57872-e27c-44e7-bde7-0448e02c78b3

            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("response status code: {} ",forEntity.getStatusCode());

            // set the hotel to rating
            rating.setHotel(hotel);

            // return the rating
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);

        return user;
    }
}
