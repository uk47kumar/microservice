package com.hotel.service.service.impl;

import com.hotel.service.entity.Hotel;
import com.hotel.service.exception.ResourceNotFoundException;
import com.hotel.service.repository.HotelRepo;
import com.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceImpl implements HotelService {

    @Autowired
    HotelRepo hotelRepo;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setId(randomHotelId);
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getById(String id) {
        return hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found !!"));
    }
}
