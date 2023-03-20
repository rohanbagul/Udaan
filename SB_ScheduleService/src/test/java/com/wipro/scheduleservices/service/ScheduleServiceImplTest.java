package com.wipro.scheduleservices.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wipro.scheduleservices.dto.ScheduleDTO;
import com.wipro.scheduleservices.dto.UpdateSeats;
import com.wipro.scheduleservices.entities.Flight;
import com.wipro.scheduleservices.entities.Route;
import com.wipro.scheduleservices.entities.Schedule;
import com.wipro.scheduleservices.entities.Seats;
import com.wipro.scheduleservices.exception.ResourceNotFoundException;
import com.wipro.scheduleservices.repository.ScheduleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {ScheduleServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ScheduleServiceImplTest {
    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;


    @Test
    public void testAddSchedule() {

        // int scheduleId = 1;
        ScheduleDTO scheduledto = new ScheduleDTO();

        scheduledto.setArrivalTime("10:00 AM");
        scheduledto.setDepartureTime("9:00 AM");
        scheduledto.setArrivalDate(LocalDate.of(2023, 3, 31).toString());
        scheduledto.setDepartureDate(LocalDate.of(2023, 3, 30).toString());
        scheduledto.setBussinessFare(1000);
        scheduledto.setEconomyFare(500);
        scheduledto.setPremiumFare(800);
        scheduledto.setLeavingLocation("Mumbai");
        scheduledto.setArrivalLocation("Delhi");
        scheduledto.setFlightNumber("AI-202");

        Schedule schedule = new Schedule();

        Route route = new Route();
        route.setSource("Mumbai");
        route.setDestination("Delhi");

        List<Seats> seats = new ArrayList<>();
        Seats seat1 = new Seats(1,"Bussiness", 10);
        Seats seat2 = new Seats(2,"Premium", 20);
        Seats seat3 = new Seats(3,"Economy", 30);
        seats.add(seat1);
        seats.add(seat2);
        seats.add(seat3);

        Flight flight = new Flight();
        flight.setFlightNumber("AI-202");
        flight.setFlightName("Air India");
        flight.setSeats(seats);

        ResponseEntity<Route> routeResponse = new ResponseEntity<>(route, HttpStatus.OK);
        ResponseEntity<Flight> flightResponse = new ResponseEntity<>(flight, HttpStatus.OK);

        when(restTemplate.getForEntity("http://localhost:8084/api/v2/getRouteBySrcAndDest/Mumbai/Delhi", Route.class))
                .thenReturn(routeResponse);
        when(restTemplate.getForEntity("http://localhost:8085/flightservice/getFlightByNum/AI-202", Flight.class))
                .thenReturn(flightResponse);

        when(scheduleRepository.save(schedule)).thenReturn(schedule);

        Schedule schedule1 = scheduleServiceImpl.addSchedule(scheduledto);
        System.out.println(schedule1);
    }

    @Test
    public void testUpdateSchedule() {
        // Set up test data
        int scheduleId = 1;
        ScheduleDTO scheduledto = new ScheduleDTO();
        scheduledto.setArrivalTime("10:00 AM");
        scheduledto.setDepartureTime("9:00 AM");
        scheduledto.setArrivalDate(LocalDate.of(2023, 3, 31).toString());
        scheduledto.setDepartureDate(LocalDate.of(2023, 3, 30).toString());
        scheduledto.setBussinessFare(1000);
        scheduledto.setEconomyFare(500);
        scheduledto.setPremiumFare(800);
        scheduledto.setLeavingLocation("Mumbai");
        scheduledto.setArrivalLocation("Delhi");
        scheduledto.setFlightNumber("AI-202");
        // Set up mock objects
        Schedule schedule = new Schedule();
        Route route = new Route();
        route.setSource("Mumbai");
        route.setDestination("Delhi");

        List<Seats> seats = new ArrayList<>();
        Seats seat1 = new Seats(1,"Bussiness", 10);
        Seats seat2 = new Seats(2,"Premium", 20);
        Seats seat3 = new Seats(3,"Economy", 30);
        seats.add(seat1);
        seats.add(seat2);
        seats.add(seat3);

        Flight flight = new Flight();
        flight.setFlightNumber("AI-202");
        flight.setFlightName("Air India");
        flight.setSeats(seats);

        ResponseEntity<Route> routeResponse = new ResponseEntity<>(route, HttpStatus.OK);
        ResponseEntity<Flight> flightResponse = new ResponseEntity<>(flight, HttpStatus.OK);
        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(schedule));
        when(restTemplate.getForEntity("http://localhost:8084/api/v2/getRouteBySrcAndDest/Mumbai/Delhi", Route.class))
                .thenReturn(routeResponse);
        when(restTemplate.getForEntity("http://localhost:8085/flightservice/getFlightByNum/AI-202", Flight.class))
                .thenReturn(flightResponse);
        when(scheduleRepository.save(schedule)).thenReturn(schedule);
        // Call the method being tested
        Schedule updatedSchedule = scheduleServiceImpl.updateSchedule(scheduleId, scheduledto);
        // Verify the results
        assertEquals(scheduledto.getArrivalTime(), updatedSchedule.getArrivalTime());
        assertEquals(scheduledto.getDepartureTime(), updatedSchedule.getDepartureTime());
        assertEquals(scheduledto.getArrivalDate(), updatedSchedule.getArrivalDate());
        assertEquals(scheduledto.getDepartureDate(), updatedSchedule.getDepartureDate());
        assertEquals(scheduledto.getBussinessFare(), updatedSchedule.getBussinessFare(), 0.0);
        assertEquals(scheduledto.getEconomyFare(), updatedSchedule.getEconomyFare(), 0.0);
        assertEquals(scheduledto.getPremiumFare(), updatedSchedule.getPremiumFare(), 0.0);
        assertEquals("Mumbai", updatedSchedule.getLeavingLocation());
        assertEquals("Delhi", updatedSchedule.getArrivalLocation());
        assertEquals("AI-202", updatedSchedule.getFlightNumber());
        assertEquals("Air India", updatedSchedule.getFlightName());
        assertTrue(updatedSchedule.isScheduled());
    }

    @Test
    void testGetAllSchedule() {
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        when(scheduleRepository.findAll()).thenReturn(scheduleList);
        List<Schedule> actualAllSchedule = scheduleServiceImpl.getAllSchedule();
        assertSame(scheduleList, actualAllSchedule);
        assertTrue(actualAllSchedule.isEmpty());
        verify(scheduleRepository).findAll();
    }


    @Test
    void testGetSchedByFlightNum() {
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        when(scheduleRepository.findByFlightNumber((String) any())).thenReturn(scheduleList);
        List<Schedule> actualSchedByFlightNum = scheduleServiceImpl.getSchedByFlightNum("42");
        assertSame(scheduleList, actualSchedByFlightNum);
        assertTrue(actualSchedByFlightNum.isEmpty());
        verify(scheduleRepository).findByFlightNumber((String) any());
    }


    @Test
    void testGetSchedByFlightName() {
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        when(scheduleRepository.findByFlightName((String) any())).thenReturn(scheduleList);
        List<Schedule> actualSchedByFlightName = scheduleServiceImpl.getSchedByFlightName("Flight Name");
        assertSame(scheduleList, actualSchedByFlightName);
        assertTrue(actualSchedByFlightName.isEmpty());
        verify(scheduleRepository).findByFlightName((String) any());
    }


    @Test
    void testGetSchedByArrTime() {
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        when(scheduleRepository.findByArrivalTime((String) any())).thenReturn(scheduleList);
        List<Schedule> actualSchedByArrTime = scheduleServiceImpl.getSchedByArrTime("Arrival Time");
        assertSame(scheduleList, actualSchedByArrTime);
        assertTrue(actualSchedByArrTime.isEmpty());
        verify(scheduleRepository).findByArrivalTime((String) any());
    }


    @Test
    void testGetSchedByDepTime() {
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        when(scheduleRepository.findByDepartureTime((String) any())).thenReturn(scheduleList);
        List<Schedule> actualSchedByDepTime = scheduleServiceImpl.getSchedByDepTime("Departure Time");
        assertSame(scheduleList, actualSchedByDepTime);
        assertTrue(actualSchedByDepTime.isEmpty());
        verify(scheduleRepository).findByDepartureTime((String) any());
    }


    @Test
    void testGetSchedByLeaveLoc() {
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        when(scheduleRepository.findByLeavingLocation((String) any())).thenReturn(scheduleList);
        List<Schedule> actualSchedByLeaveLoc = scheduleServiceImpl.getSchedByLeaveLoc("Leaving Location");
        assertSame(scheduleList, actualSchedByLeaveLoc);
        assertTrue(actualSchedByLeaveLoc.isEmpty());
        verify(scheduleRepository).findByLeavingLocation((String) any());
    }


    @Test
    void testGetSchedByArrLoc() {
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        when(scheduleRepository.findByArrivalLocation((String) any())).thenReturn(scheduleList);
        List<Schedule> actualSchedByArrLoc = scheduleServiceImpl.getSchedByArrLoc("Arrival Location");
        assertSame(scheduleList, actualSchedByArrLoc);
        assertTrue(actualSchedByArrLoc.isEmpty());
        verify(scheduleRepository).findByArrivalLocation((String) any());
    }


    @Test
    void testDeleteSchedule() {
        Schedule schedule = new Schedule();
        schedule.setArrivalDate("2020-03-01");
        schedule.setArrivalLocation("Arrival Location");
        schedule.setArrivalTime("Arrival Time");
        schedule.setBussinessFare(1);
        schedule.setDepartureDate("2020-03-01");
        schedule.setDepartureTime("Departure Time");
        schedule.setEconomyFare(1);
        schedule.setFlightName("Flight Name");
        schedule.setFlightNumber("42");
        schedule.setLeavingLocation("Leaving Location");
        schedule.setPremiumFare(1);
        schedule.setScheduleId(1);
        schedule.setScheduled(true);
        Optional<Schedule> ofResult = Optional.of(schedule);
        doNothing().when(scheduleRepository).deleteById((Integer) any());
        when(scheduleRepository.findById((Integer) any())).thenReturn(ofResult);
        scheduleServiceImpl.deleteSchedule(1);
        verify(scheduleRepository).findById((Integer) any());
        verify(scheduleRepository).deleteById((Integer) any());
        assertTrue(scheduleServiceImpl.getAllSchedule().isEmpty());
    }


    @Test
    void testGetScheduleById() {
        Schedule schedule = new Schedule();
        schedule.setArrivalDate("2020-03-01");
        schedule.setArrivalLocation("Arrival Location");
        schedule.setArrivalTime("Arrival Time");
        schedule.setBussinessFare(1);
        schedule.setDepartureDate("2020-03-01");
        schedule.setDepartureTime("Departure Time");
        schedule.setEconomyFare(1);
        schedule.setFlightName("Flight Name");
        schedule.setFlightNumber("42");
        schedule.setLeavingLocation("Leaving Location");
        schedule.setPremiumFare(1);
        schedule.setScheduleId(1);
        schedule.setScheduled(true);
        Optional<Schedule> ofResult = Optional.of(schedule);
        when(scheduleRepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(schedule, scheduleServiceImpl.getScheduleById(1));
        verify(scheduleRepository).findById((Integer) any());
    }


    @Test
    void testUpdateSeatData() {
        Schedule schedule = new Schedule();
        schedule.setArrivalDate("2020-03-01");
        schedule.setArrivalLocation("Arrival Location");
        schedule.setArrivalTime("Arrival Time");
        schedule.setBussinessFare(1);
        schedule.setBussinessSeats(1);
        schedule.setDepartureDate("2020-03-01");
        schedule.setDepartureTime("Departure Time");
        schedule.setEconomyFare(1);
        schedule.setEconomySeats(1);
        schedule.setFlightName("Flight Name");
        schedule.setFlightNumber("42");
        schedule.setLeavingLocation("Leaving Location");
        schedule.setPremiumFare(1);
        schedule.setPremiumSeats(1);
        schedule.setScheduleId(1);
        schedule.setScheduled(true);
        Optional<Schedule> ofResult = Optional.of(schedule);

        Schedule schedule1 = new Schedule();
        schedule1.setArrivalDate("2020-03-01");
        schedule1.setArrivalLocation("Arrival Location");
        schedule1.setArrivalTime("Arrival Time");
        schedule1.setBussinessFare(1);
        schedule1.setBussinessSeats(1);
        schedule1.setDepartureDate("2020-03-01");
        schedule1.setDepartureTime("Departure Time");
        schedule1.setEconomyFare(1);
        schedule1.setEconomySeats(1);
        schedule1.setFlightName("Flight Name");
        schedule1.setFlightNumber("42");
        schedule1.setLeavingLocation("Leaving Location");
        schedule1.setPremiumFare(1);
        schedule1.setPremiumSeats(1);
        schedule1.setScheduleId(1);
        schedule1.setScheduled(true);
        when(scheduleRepository.save((Schedule) any())).thenReturn(schedule1);
        when(scheduleRepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(schedule1, scheduleServiceImpl.updateSeatData(1, new UpdateSeats("42", "Seat Class", 1)));
        verify(scheduleRepository).save((Schedule) any());
        verify(scheduleRepository).findById((Integer) any());
    }

    @Test
    void testGetSchedByleaveLocAndArrLoc() {
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        when(scheduleRepository.findByLeavingLocationAndArrivalLocation((String) any(), (String) any()))
                .thenReturn(scheduleList);
        List<Schedule> actualSchedByleaveLocAndArrLoc = scheduleServiceImpl
                .getSchedByleaveLocAndArrLoc("Leaving Location", "Arrival Location");
        assertSame(scheduleList, actualSchedByleaveLocAndArrLoc);
        assertTrue(actualSchedByleaveLocAndArrLoc.isEmpty());
        verify(scheduleRepository).findByLeavingLocationAndArrivalLocation((String) any(), (String) any());
    }

    @Test
    void testGetSchedByleaveLocAndArrLoc2() {
        when(scheduleRepository.findByLeavingLocationAndArrivalLocation((String) any(), (String) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class,
                () -> scheduleServiceImpl.getSchedByleaveLocAndArrLoc("Leaving Location", "Arrival Location"));
        verify(scheduleRepository).findByLeavingLocationAndArrivalLocation((String) any(), (String) any());
    }

}

