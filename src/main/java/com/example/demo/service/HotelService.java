package com.example.demo.service;

import com.example.demo.model.Hotel;
import java.util.List;
import java.util.Optional;

public interface HotelService {
    List<Hotel> getAllHoteles();
    Optional<Hotel> getHotelById(Long id);
    Hotel createHotel(Hotel hotel);
    Hotel updateHotel(Long id, Hotel hotel);
    void deleteHotel(Long id);
    List<Hotel> getHotelesDisponibles(); 

}