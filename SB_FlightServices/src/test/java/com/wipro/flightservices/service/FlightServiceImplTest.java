package com.wipro.flightservices.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wipro.flightservices.dto.FlightDTO;
import com.wipro.flightservices.entities.Flight;
import com.wipro.flightservices.entities.Seats;
import com.wipro.flightservices.exception.CustomException;
import com.wipro.flightservices.exception.MyBadRequestException;
import com.wipro.flightservices.repository.FlightRepository;
import com.wipro.flightservices.repository.SeatRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FlightServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FlightServiceImplTest {
    @MockBean
    private FlightRepository flightRepository;

    @Autowired
    private FlightServiceImpl flightServiceImpl;

    @MockBean
    private SeatRepository seatRepository;


    @Test
    void testAddFlight() {
        Flight flight = new Flight();
        flight.setFlightName("Flight Name");
        flight.setFlightNumber("42");
        flight.setId(1);
        flight.setSeats(new ArrayList<>());

        Flight flight1 = new Flight();
        flight1.setFlightName("Flight Name");
        flight1.setFlightNumber("42");
        flight1.setId(1);
        flight1.setSeats(new ArrayList<>());
        when(flightRepository.findByFlightNumber((String) any())).thenReturn(flight);
        when(flightRepository.save((Flight) any())).thenReturn(flight1);
        assertThrows(MyBadRequestException.class, () -> flightServiceImpl.addFlight(new FlightDTO()));
        verify(flightRepository).findByFlightNumber((String) any());
    }




    @Test
    void testAddFlight3() {
        when(flightRepository.findByFlightNumber((String) any())).thenThrow(new CustomException("An error occurred"));
        when(flightRepository.save((Flight) any())).thenThrow(new CustomException("An error occurred"));
        assertThrows(CustomException.class, () -> flightServiceImpl.addFlight(new FlightDTO()));
        verify(flightRepository).findByFlightNumber((String) any());
    }


    @Test
    void testGetAllFlights() {
        ArrayList<Flight> flightList = new ArrayList<>();
        when(flightRepository.findAll()).thenReturn(flightList);
        List<Flight> actualAllFlights = flightServiceImpl.getAllFlights();
        assertSame(flightList, actualAllFlights);
        assertTrue(actualAllFlights.isEmpty());
        verify(flightRepository).findAll();
    }


    @Test
    void testGetAllFlights2() {
        when(flightRepository.findAll()).thenThrow(new MyBadRequestException("An error occurred"));
        assertThrows(MyBadRequestException.class, () -> flightServiceImpl.getAllFlights());
        verify(flightRepository).findAll();
    }

   
    @Test
    void testDeleteFlight() {
        Flight flight = new Flight();
        flight.setFlightName("Flight Name");
        flight.setFlightNumber("42");
        flight.setId(1);
        flight.setSeats(new ArrayList<>());
        doNothing().when(flightRepository).deleteById((Integer) any());
        when(flightRepository.findById(anyInt())).thenReturn(flight);
        when(seatRepository.findByFlight(anyInt())).thenReturn(new ArrayList<>());
        doNothing().when(seatRepository).deleteAll((Iterable<Seats>) any());
        flightServiceImpl.deleteFlight(1);
        verify(flightRepository).findById(anyInt());
        verify(flightRepository).deleteById((Integer) any());
        verify(seatRepository).findByFlight(anyInt());
        verify(seatRepository).deleteAll((Iterable<Seats>) any());
        assertTrue(flightServiceImpl.getAllFlights().isEmpty());
    }


    @Test
    void testDeleteFlight2() {
        Flight flight = new Flight();
        flight.setFlightName("Flight Name");
        flight.setFlightNumber("42");
        flight.setId(1);
        flight.setSeats(new ArrayList<>());
        doNothing().when(flightRepository).deleteById((Integer) any());
        when(flightRepository.findById(anyInt())).thenReturn(flight);
        when(seatRepository.findByFlight(anyInt())).thenThrow(new CustomException("An error occurred"));
        doThrow(new CustomException("An error occurred")).when(seatRepository).deleteAll((Iterable<Seats>) any());
        assertThrows(CustomException.class, () -> flightServiceImpl.deleteFlight(1));
        verify(flightRepository).findById(anyInt());
        verify(seatRepository).findByFlight(anyInt());
    }
}

