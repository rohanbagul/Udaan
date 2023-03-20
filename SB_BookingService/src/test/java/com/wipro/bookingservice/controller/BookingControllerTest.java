package com.wipro.bookingservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.bookingservice.dto.BookingDTO;
import com.wipro.bookingservice.entities.Booking;
import com.wipro.bookingservice.service.BookingService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BookingController.class})
@ExtendWith(SpringExtension.class)
class BookingControllerTest {
    @Autowired
    private BookingController bookingController;

    @MockBean
    private BookingService bookingService;

  
    @Test
    void testCancelBooking() throws Exception {
        doNothing().when(bookingService).deleteBooking(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/bookingservice/cancelBooking/{id}",
                1);
        MockMvcBuilders.standaloneSetup(bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Flight Canceld"));
    }

  
    @Test
    void testCreateBooking() throws Exception {
        Booking booking = new Booking();
        booking.setArrivalDate("2020-03-01");
        booking.setArrivalLocation("Arrival Location");
        booking.setArrivalTime("Arrival Time");
        booking.setBookingDate("2020-03-01");
        booking.setBookingId("42");
        booking.setDepartureDate("2020-03-01");
        booking.setDepartureLocation("Departure Location");
        booking.setDepartureTime("Departure Time");
        booking.setFlightName("Flight Name");
        booking.setFlightNumber("42");
        booking.setId(1);
        booking.setPassenger(new ArrayList<>());
        booking.setScheduleId(1);
        booking.setSeatClass("Seat Class");
        booking.setStatus("Status");
        booking.setTotalAmount(10.0d);
        booking.setUserId(1);
        when(bookingService.createBooking((BookingDTO) any())).thenReturn(booking);

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setPassengers(new ArrayList<>());
        bookingDTO.setScheduleId(1);
        bookingDTO.setSeatClass("Seat Class");
        bookingDTO.setStatus("Status");
        bookingDTO.setUserId(1);
        String content = (new ObjectMapper()).writeValueAsString(bookingDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/bookingservice/createBooking")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(bookingController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"bookingId\":\"42\",\"bookingDate\":\"2020-03-01\",\"scheduleId\":1,\"userId\":1,\"flightNumber\":\"42\","
                                        + "\"flightName\":\"Flight Name\",\"arrivalDate\":\"2020-03-01\",\"departureDate\":\"2020-03-01\",\"arrivalTime\":\"Arrival"
                                        + " Time\",\"departureTime\":\"Departure Time\",\"arrivalLocation\":\"Arrival Location\",\"departureLocation\":\"Departure"
                                        + " Location\",\"seatClass\":\"Seat Class\",\"totalAmount\":10.0,\"status\":\"Status\",\"passenger\":[]}"));
    }

   
    @Test
    void testGetBookingByBookingId() throws Exception {
        Booking booking = new Booking();
        booking.setArrivalDate("2020-03-01");
        booking.setArrivalLocation("Arrival Location");
        booking.setArrivalTime("Arrival Time");
        booking.setBookingDate("2020-03-01");
        booking.setBookingId("42");
        booking.setDepartureDate("2020-03-01");
        booking.setDepartureLocation("Departure Location");
        booking.setDepartureTime("Departure Time");
        booking.setFlightName("Flight Name");
        booking.setFlightNumber("42");
        booking.setId(1);
        booking.setPassenger(new ArrayList<>());
        booking.setScheduleId(1);
        booking.setSeatClass("Seat Class");
        booking.setStatus("Status");
        booking.setTotalAmount(10.0d);
        booking.setUserId(1);
        when(bookingService.getBookingByBookingId((String) any())).thenReturn(booking);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/bookingservice/getBookingsById/{bookingId}", "42");
        MockMvcBuilders.standaloneSetup(bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"bookingId\":\"42\",\"bookingDate\":\"2020-03-01\",\"scheduleId\":1,\"userId\":1,\"flightNumber\":\"42\","
                                        + "\"flightName\":\"Flight Name\",\"arrivalDate\":\"2020-03-01\",\"departureDate\":\"2020-03-01\",\"arrivalTime\":\"Arrival"
                                        + " Time\",\"departureTime\":\"Departure Time\",\"arrivalLocation\":\"Arrival Location\",\"departureLocation\":\"Departure"
                                        + " Location\",\"seatClass\":\"Seat Class\",\"totalAmount\":10.0,\"status\":\"Status\",\"passenger\":[]}"));
    }

   
    @Test
    void testUpdateBooking() throws Exception {
        Booking booking = new Booking();
        booking.setArrivalDate("2020-03-01");
        booking.setArrivalLocation("Arrival Location");
        booking.setArrivalTime("Arrival Time");
        booking.setBookingDate("2020-03-01");
        booking.setBookingId("42");
        booking.setDepartureDate("2020-03-01");
        booking.setDepartureLocation("Departure Location");
        booking.setDepartureTime("Departure Time");
        booking.setFlightName("Flight Name");
        booking.setFlightNumber("42");
        booking.setId(1);
        booking.setPassenger(new ArrayList<>());
        booking.setScheduleId(1);
        booking.setSeatClass("Seat Class");
        booking.setStatus("Status");
        booking.setTotalAmount(10.0d);
        booking.setUserId(1);
        when(bookingService.updateBooking(anyInt(), (BookingDTO) any())).thenReturn(booking);

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setPassengers(new ArrayList<>());
        bookingDTO.setScheduleId(1);
        bookingDTO.setSeatClass("Seat Class");
        bookingDTO.setStatus("Status");
        bookingDTO.setUserId(1);
        String content = (new ObjectMapper()).writeValueAsString(bookingDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/bookingservice/updateBooking/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(bookingController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"bookingId\":\"42\",\"bookingDate\":\"2020-03-01\",\"scheduleId\":1,\"userId\":1,\"flightNumber\":\"42\","
                                        + "\"flightName\":\"Flight Name\",\"arrivalDate\":\"2020-03-01\",\"departureDate\":\"2020-03-01\",\"arrivalTime\":\"Arrival"
                                        + " Time\",\"departureTime\":\"Departure Time\",\"arrivalLocation\":\"Arrival Location\",\"departureLocation\":\"Departure"
                                        + " Location\",\"seatClass\":\"Seat Class\",\"totalAmount\":10.0,\"status\":\"Status\",\"passenger\":[]}"));
    }

    
    @Test
    void testCancelBooking2() throws Exception {
        doNothing().when(bookingService).deleteBooking(anyInt());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/bookingservice/cancelBooking/{id}",
                1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(bookingController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Flight Canceld"));
    }

  
    @Test
    void testGetBookingByUserId() throws Exception {
        when(bookingService.getBookingsByUserId(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/bookingservice/getBookingByUserId/{userId}", 1);
        MockMvcBuilders.standaloneSetup(bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
 
   
    @Test
    void testGetBookings() throws Exception {
        when(bookingService.getAllBookings()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bookingservice/getBookings");
        MockMvcBuilders.standaloneSetup(bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

   
}

