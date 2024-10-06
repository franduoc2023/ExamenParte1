package com.example.demo.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;
import com.example.demo.service.HotelServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

  

public class HotelServiceImplTest {

@InjectMocks
private HotelServiceImpl hotelService;
@Mock
    private HotelRepository hotelRepository;
@BeforeEach
public void setUp() {
    MockitoAnnotations.openMocks(this);
}

    @Test
    public void testGetAllHotelesSize() {
     Hotel hotel1 = new Hotel();
        hotel1.setId(1L);
        hotel1.setNombreHabitacion("Suite Ejecutiva");

        Hotel hotel2 = new Hotel();
        hotel2.setId(2L);
        hotel2.setNombreHabitacion("Habitación Doble");
        List<Hotel> hotelList = Arrays.asList(hotel1, hotel2);
                when(hotelRepository.findAll()).thenReturn(hotelList);

        List<Hotel> result = hotelService.getAllHoteles();
assertEquals(2, result.size());    }

@Test
public void testDeleteHotel() {
    Long hotelId = 1L;
        doNothing().when(hotelRepository).deleteById(hotelId);
        hotelService.deleteHotel(hotelId);
        verify(hotelRepository).deleteById(hotelId);


}}
