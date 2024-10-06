package com.example.demo.model;


 import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
 import jakarta.persistence.Table;

 import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;



 

@Entity
@Table(name = "Huesped")
public class Huesped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

 
    @Column(name = "nombreHuesped")
    private String nombreHuesped;

    @Column(name = "fechaAlojamiento")
    private LocalDate fechaAlojamiento;

    @ManyToOne
    @JoinColumn(name = "hotel_id")   
    @JsonBackReference
    private Hotel hotel;
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreHuesped() {
        return nombreHuesped;
    }

    public void setNombreHuesped(String nombreHuesped) {
        this.nombreHuesped = nombreHuesped;
    }

    public LocalDate getFechaAlojamiento() {
        return fechaAlojamiento;
    }

    public void setFechaAlojamiento(LocalDate fechaAlojamiento) {
        this.fechaAlojamiento = fechaAlojamiento;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}

 


