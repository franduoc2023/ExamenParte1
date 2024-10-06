package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Hotel;
import com.example.demo.model.Huesped;
import com.example.demo.repository.HotelRepository;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllHoteles() {
        return hotelRepository.findAll();
    }
    @Override
    public List<Hotel> getHotelesDisponibles() {
        List<Hotel> allHoteles = hotelRepository.findAll();
        
        return allHoteles.stream()
                         .filter(hotel -> hotel.getHuespedes() == null || hotel.getHuespedes().isEmpty())
                         .collect(Collectors.toList());
    }
    @Override
    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }


    @Override
    public Hotel updateHotel(Long id, Hotel hotel) {
        hotel.setId(id);   
        Hotel updatedHotel = hotelRepository.save(hotel);   
    
        if (hotel.getHuespedes() != null && !hotel.getHuespedes().isEmpty()) {
             Huesped primerHuesped = hotel.getHuespedes().get(0);  
            updatedHotel.setNombreHabitacion(primerHuesped.getNombreHuesped());  
             for (Huesped huesped : hotel.getHuespedes()) {
                huesped.setHotel(updatedHotel);
            }
        }
    
        return hotelRepository.save(updatedHotel);   
    }  


    @Override
    public Hotel createHotel(Hotel hotel) {
    
        if (hotel.getHuespedes() != null && !hotel.getHuespedes().isEmpty()) {
            for (Huesped huesped : hotel.getHuespedes()) {
                huesped.setHotel(hotel);
            }
        }
        
        return hotelRepository.save(hotel);  
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);  
    }



}
