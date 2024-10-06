package com.example.demo.controllerTest;

import com.example.demo.controller.HotelController;
import com.example.demo.model.Hotel;
import com.example.demo.service.HotelServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;  

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(HotelController.class)
public class HotelControllerTest {

@Autowired
    private MockMvc mockMvc;

@MockBean
    private HotelServiceImpl hotelServiceMock;

@Test
public void disponibilidadTest() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setFechaDisponible(LocalDate.of(2024, 10, 15));
        hotel.setNombreHabitacion("Suite Ejecutiva");
        hotel.setHuespedes(Collections.emptyList());

        Mockito.when(hotelServiceMock.getHotelById(1L)).thenReturn(Optional.of(hotel));

        mockMvc.perform(get("/alojamientos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreHabitacion").value("Suite Ejecutiva"))
                .andExpect(jsonPath("$.huespedes").isEmpty());
    }

    @Test
    public void testDeleteHotel() throws Exception {
         Mockito.doNothing().when(hotelServiceMock).deleteHotel(1L);
    
         mockMvc.perform(delete("/alojamientos/1"))
                .andExpect(status().isNoContent()); 
    }}