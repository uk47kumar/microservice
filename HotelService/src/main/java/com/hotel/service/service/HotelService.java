package com.hotel.service.service;

import com.hotel.service.entity.Hotel;

import java.util.List;

public interface HotelService {

    // create
    Hotel saveHotel(Hotel hotel);

    // get all
    List<Hotel> getAllHotel();

    // get by id
    Hotel getById(String id);
}
