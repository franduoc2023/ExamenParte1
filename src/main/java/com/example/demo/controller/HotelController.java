package com.example.demo.controller;

import com.example.demo.model.Hotel;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alojamientos")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @GetMapping("/disponibles")
    public List<Hotel> getHotelesDisponibles() {
        return hotelService.getHotelesDisponibles();
    }
     @GetMapping
    public List<Hotel> getAllHoteles() {
        return hotelService.getAllHoteles();
    }

@GetMapping("/{id}")
public ResponseEntity<EntityModel<Hotel>> getHotelById(@PathVariable Long id) {
    Optional<Hotel> hotel = hotelService.getHotelById(id);
    if (hotel.isPresent()) {
        EntityModel<Hotel> resource = EntityModel.of(hotel.get());
        resource.add(linkTo(methodOn(HotelController.class).getAllHoteles()).withRel("all-hotels"));
        resource.add(linkTo(methodOn(HotelController.class).deleteHotel(id)).withRel("delete-hotel"));
        resource.add(linkTo(methodOn(HotelController.class).updateHotel(id, hotel.get())).withRel("update-hotel"));
        return ResponseEntity.ok(resource);
    } else {
        return ResponseEntity.notFound().build();
    }
}




    
     @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel createdHotel = hotelService.createHotel(hotel);
        return ResponseEntity.ok(createdHotel);
    }

     @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        Hotel updatedHotel = hotelService.updateHotel(id, hotel);
        return ResponseEntity.ok(updatedHotel);
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}