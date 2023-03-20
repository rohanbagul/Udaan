package com.wipro.bookingservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wipro.bookingservice.dto.BookingDTO;
import com.wipro.bookingservice.entities.Booking;
import com.wipro.bookingservice.entities.Passenger;
import com.wipro.bookingservice.entities.Schedule;
import com.wipro.bookingservice.repository.BookingRepository;
import com.wipro.bookingservice.repository.PassengerRepository;

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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {BookingServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookingServiceImplTest {
    @MockBean
    private BookingRepository bookingRepository;

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @MockBean
    private PassengerRepository passengerRepository;

    @MockBean
    private RestTemplate restTemplate;


    @Test
    void testCreateBooking5() throws RestClientException {
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
        when(bookingRepository.save((Booking) any())).thenReturn(booking);
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Schedule>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        when(restTemplate.getForEntity((String) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        Schedule schedule = new Schedule(1, "Arrival Time", "Departure Time", "2020-03-01", "2020-03-01", 1, 1, 1, 1, 1,
                1, "Arrival Location", "Leaving Location", "42", "Flight Name", true);

        when(restTemplate.getForEntity((String) any(), (Class<Schedule>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(schedule, HttpStatus.CONTINUE));
        assertSame(booking,
                bookingServiceImpl.createBooking(new BookingDTO(1, 1, "Seat Class", "Status", new ArrayList<>())));
        verify(bookingRepository).save((Booking) any());
        verify(restTemplate, atLeast(1)).getForEntity((String) any(), (Class<Schedule>) any(), (Object[]) any());
        verify(restTemplate).postForEntity((String) any(), (Object) any(), (Class<Schedule>) any(), (Object[]) any());
        assertSame(schedule, bookingServiceImpl.schedule);
    }
    
  


 
    @Test
    void testToatalAmount1() {
        BookingDTO bookingDTO = mock(BookingDTO.class);
        when(bookingDTO.getPassengers()).thenReturn(new ArrayList<>());
        assertEquals(0.0d, bookingServiceImpl.toatalAmount(bookingDTO));
        verify(bookingDTO).getPassengers();
    }

    @Test
    void testToatalAmount2() {
        Passenger passenger = new Passenger();
        passenger.setPassengerAge(1);
        passenger.setPassengerCountry("GB");
        passenger.setPassengerEmail("jane.doe@example.org");
        passenger.setPassengerGender("Passenger Gender");
        passenger.setPassengerMobile("Passenger Mobile");
        passenger.setPassengerName("Passenger Name");
        passenger.setPid(1);

        ArrayList<Passenger> passengerList = new ArrayList<>();
        passengerList.add(passenger);
        BookingDTO bookingDTO = mock(BookingDTO.class);
        when(bookingDTO.getSeatClass()).thenReturn("Seat Class");
        when(bookingDTO.getPassengers()).thenReturn(passengerList);
        assertEquals(0.0d, bookingServiceImpl.toatalAmount(bookingDTO));
        verify(bookingDTO, atLeast(1)).getSeatClass();
        verify(bookingDTO).getPassengers();
    }

    @Test
    void testGetAllBookings() {
        ArrayList<Booking> bookingList = new ArrayList<>();
        when(bookingRepository.findAll()).thenReturn(bookingList);
        List<Booking> actualAllBookings = bookingServiceImpl.getAllBookings();
        assertSame(bookingList, actualAllBookings);
        assertTrue(actualAllBookings.isEmpty());
        verify(bookingRepository).findAll();
    }

    @Test
    void testGetBookingByBookingId() {
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
        when(bookingRepository.findByBookingId((String) any())).thenReturn(booking);
        assertSame(booking, bookingServiceImpl.getBookingByBookingId("42"));
        verify(bookingRepository).findByBookingId((String) any());
    }

    @Test
    void testDeleteBooking() {
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
        Optional<Booking> ofResult = Optional.of(booking);
        doNothing().when(bookingRepository).deleteById((Integer) any());
        when(bookingRepository.findById((Integer) any())).thenReturn(ofResult);
        bookingServiceImpl.deleteBooking(1);
        verify(bookingRepository).findById((Integer) any());
        verify(bookingRepository).deleteById((Integer) any());
        assertTrue(bookingServiceImpl.getAllBookings().isEmpty());
    }

    @Test
    void testGetBookingsByUserId() {
        ArrayList<Booking> bookingList = new ArrayList<>();
        when(bookingRepository.findByUserId(anyInt())).thenReturn(bookingList);
        List<Booking> actualBookingsByUserId = bookingServiceImpl.getBookingsByUserId(1);
        assertSame(bookingList, actualBookingsByUserId);
        assertTrue(actualBookingsByUserId.isEmpty());
        verify(bookingRepository).findByUserId(anyInt());
    }
}

