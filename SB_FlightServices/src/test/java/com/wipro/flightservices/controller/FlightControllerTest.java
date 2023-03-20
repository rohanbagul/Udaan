package com.wipro.flightservices.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.flightservices.dto.FlightDTO;
import com.wipro.flightservices.entities.Flight;
import com.wipro.flightservices.service.FlightService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FlightController.class})
@ExtendWith(SpringExtension.class)
class FlightControllerTest {
    @Autowired
    private FlightController flightController;

    @MockBean
    private FlightService flightService;


    @Test
    void testAddFlight() throws Exception {
        Flight flight = new Flight();
        flight.setFlightName("Flight Name");
        flight.setFlightNumber("42");
        flight.setId(1);
        flight.setSeats(new ArrayList<>());
        when(flightService.addFlight((FlightDTO) any())).thenReturn(flight);

        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightName("Flight Name");
        flightDTO.setFlightNumber("42");
        flightDTO.setSeats(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(flightDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/flightservice/addFlight")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(flightController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"flightNumber\":\"42\",\"flightName\":\"Flight Name\",\"seats\":[]}"));
    }


    @Test
    void testDeleteFlight() throws Exception {
        doNothing().when(flightService).deleteFlight(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/flightservice/deleteFlight/{flightID}", 1);
        MockMvcBuilders.standaloneSetup(flightController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Flight Data Deleted"));
    }


    @Test
    void testDeleteFlight2() throws Exception {
        doNothing().when(flightService).deleteFlight(anyInt());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders
                .delete("/flightservice/deleteFlight/{flightID}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(flightController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Flight Data Deleted"));
    }


    @Test
    void testUpdateFlight() throws Exception {
        Flight flight = new Flight();
        flight.setFlightName("Flight Name");
        flight.setFlightNumber("42");
        flight.setId(1);
        flight.setSeats(new ArrayList<>());
        when(flightService.updateFlight(anyInt(), (FlightDTO) any())).thenReturn(flight);

        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightName("Flight Name");
        flightDTO.setFlightNumber("42");
        flightDTO.setSeats(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(flightDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/flightservice/updateFlight/{flightID}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(flightController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"flightNumber\":\"42\",\"flightName\":\"Flight Name\",\"seats\":[]}"));
    }


    @Test
    void testGetFlightByFlightNum() throws Exception {
        Flight flight = new Flight();
        flight.setFlightName("Flight Name");
        flight.setFlightNumber("42");
        flight.setId(1);
        flight.setSeats(new ArrayList<>());
        when(flightService.getFlightByFlightNumber((String) any())).thenReturn(flight);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/flightservice/getFlightByNum/{flightNumber}", "42");
        MockMvcBuilders.standaloneSetup(flightController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"flightNumber\":\"42\",\"flightName\":\"Flight Name\",\"seats\":[]}"));
    }


    @Test
    void testGetFlightById() throws Exception {
        Flight flight = new Flight();
        flight.setFlightName("Flight Name");
        flight.setFlightNumber("42");
        flight.setId(1);
        flight.setSeats(new ArrayList<>());
        when(flightService.getByFlightId(anyInt())).thenReturn(flight);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flightservice/getFlight/{flightID}",
                1);
        MockMvcBuilders.standaloneSetup(flightController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"flightNumber\":\"42\",\"flightName\":\"Flight Name\",\"seats\":[]}"));
    }


    @Test
    void testGetFlights() throws Exception {
        when(flightService.getAllFlights()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flightservice/getFlight");
        MockMvcBuilders.standaloneSetup(flightController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetFlights2() throws Exception {
        when(flightService.getAllFlights()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/flightservice/getFlight");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(flightController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

