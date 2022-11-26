package com.hotel.service.controller;

import com.hotel.service.entity.Hotel;
import com.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;

    // create
    @PostMapping("")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    // get All
    @GetMapping("")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> allHotel = hotelService.getAllHotel();
        return ResponseEntity.ok(allHotel);
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getById(@PathVariable("id") String id){
        Hotel byId = hotelService.getById(id);
        return ResponseEntity.ok(byId);
    }

}
