package com.wipro.scheduleservices.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.scheduleservices.dto.ScheduleDTO;
import com.wipro.scheduleservices.dto.UpdateSeats;
import com.wipro.scheduleservices.entities.Schedule;
import com.wipro.scheduleservices.service.ScheduleService;

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

@ContextConfiguration(classes = {ScheduleController.class})
@ExtendWith(SpringExtension.class)
class ScheduleControllerTest {
    @Autowired
    private ScheduleController scheduleController;

    @MockBean
    private ScheduleService scheduleService;

    @Test
    void testAddSchedule() throws Exception {
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
        when(scheduleService.addSchedule((ScheduleDTO) any())).thenReturn(schedule);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setArrivalDate("2020-03-01");
        scheduleDTO.setArrivalLocation("Arrival Location");
        scheduleDTO.setArrivalTime("Arrival Time");
        scheduleDTO.setBussinessFare(1);
        scheduleDTO.setDepartureDate("2020-03-01");
        scheduleDTO.setDepartureTime("Departure Time");
        scheduleDTO.setEconomyFare(1);
        scheduleDTO.setFlightName("Flight Name");
        scheduleDTO.setFlightNumber("42");
        scheduleDTO.setLeavingLocation("Leaving Location");
        scheduleDTO.setPremiumFare(1);
        scheduleDTO.setScheduled(true);
        String content = (new ObjectMapper()).writeValueAsString(scheduleDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/scheduleService/addSchedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(scheduleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"scheduleId\":1,\"arrivalTime\":\"Arrival Time\",\"departureTime\":\"Departure Time\",\"arrivalDate\":\"2020-03"
                                        + "-01\",\"departureDate\":\"2020-03-01\",\"bussinessFare\":1,\"economyFare\":1,\"premiumFare\":1,\"bussinessSeats\""
                                        + ":1,\"economySeats\":1,\"premiumSeats\":1,\"arrivalLocation\":\"Arrival Location\",\"leavingLocation\":\"Leaving"
                                        + " Location\",\"flightNumber\":\"42\",\"flightName\":\"Flight Name\",\"scheduled\":true}"));
    }


    @Test
    void testGetScheduleById() throws Exception {
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
        when(scheduleService.getScheduleById(anyInt())).thenReturn(schedule);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/scheduleService/getSchedById/{scheduleId}", 1);
        MockMvcBuilders.standaloneSetup(scheduleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"scheduleId\":1,\"arrivalTime\":\"Arrival Time\",\"departureTime\":\"Departure Time\",\"arrivalDate\":\"2020-03"
                                        + "-01\",\"departureDate\":\"2020-03-01\",\"bussinessFare\":1,\"economyFare\":1,\"premiumFare\":1,\"bussinessSeats\""
                                        + ":1,\"economySeats\":1,\"premiumSeats\":1,\"arrivalLocation\":\"Arrival Location\",\"leavingLocation\":\"Leaving"
                                        + " Location\",\"flightNumber\":\"42\",\"flightName\":\"Flight Name\",\"scheduled\":true}"));
    }


    @Test
    void testUpdateSchedule() throws Exception {
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
        when(scheduleService.updateSchedule(anyInt(), (ScheduleDTO) any())).thenReturn(schedule);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setArrivalDate("2020-03-01");
        scheduleDTO.setArrivalLocation("Arrival Location");
        scheduleDTO.setArrivalTime("Arrival Time");
        scheduleDTO.setBussinessFare(1);
        scheduleDTO.setDepartureDate("2020-03-01");
        scheduleDTO.setDepartureTime("Departure Time");
        scheduleDTO.setEconomyFare(1);
        scheduleDTO.setFlightName("Flight Name");
        scheduleDTO.setFlightNumber("42");
        scheduleDTO.setLeavingLocation("Leaving Location");
        scheduleDTO.setPremiumFare(1);
        scheduleDTO.setScheduled(true);
        String content = (new ObjectMapper()).writeValueAsString(scheduleDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/scheduleService/updateSchedule/{scheduleId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(scheduleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"scheduleId\":1,\"arrivalTime\":\"Arrival Time\",\"departureTime\":\"Departure Time\",\"arrivalDate\":\"2020-03"
                                        + "-01\",\"departureDate\":\"2020-03-01\",\"bussinessFare\":1,\"economyFare\":1,\"premiumFare\":1,\"bussinessSeats\""
                                        + ":1,\"economySeats\":1,\"premiumSeats\":1,\"arrivalLocation\":\"Arrival Location\",\"leavingLocation\":\"Leaving"
                                        + " Location\",\"flightNumber\":\"42\",\"flightName\":\"Flight Name\",\"scheduled\":true}"));
    }


    @Test
    void testUpdateSeatData() throws Exception {
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
        when(scheduleService.updateSeatData(anyInt(), (UpdateSeats) any())).thenReturn(schedule);

        UpdateSeats updateSeats = new UpdateSeats();
        updateSeats.setBookedSeats(1);
        updateSeats.setFlightNumber("42");
        updateSeats.setSeatClass("Seat Class");
        String content = (new ObjectMapper()).writeValueAsString(updateSeats);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/scheduleService/updateSeatData/{scheduleId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(scheduleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"scheduleId\":1,\"arrivalTime\":\"Arrival Time\",\"departureTime\":\"Departure Time\",\"arrivalDate\":\"2020-03"
                                        + "-01\",\"departureDate\":\"2020-03-01\",\"bussinessFare\":1,\"economyFare\":1,\"premiumFare\":1,\"bussinessSeats\""
                                        + ":1,\"economySeats\":1,\"premiumSeats\":1,\"arrivalLocation\":\"Arrival Location\",\"leavingLocation\":\"Leaving"
                                        + " Location\",\"flightNumber\":\"42\",\"flightName\":\"Flight Name\",\"scheduled\":true}"));
    }


    @Test
    void testDeleteSchedule() throws Exception {
        doNothing().when(scheduleService).deleteSchedule(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/scheduleService/deleteSchedule/{scheduleId}", 1);
        MockMvcBuilders.standaloneSetup(scheduleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testGetSchedByArrTime() throws Exception {
        when(scheduleService.getSchedByArrTime((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/scheduleService/getSchedByArrTime/{arrivalTime}", "Arrival Time");
        MockMvcBuilders.standaloneSetup(scheduleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetSchedByDepTime() throws Exception {
        when(scheduleService.getSchedByDepTime((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/scheduleService/getSchedByDeptTime/{departureTime}", "Departure Time");
        MockMvcBuilders.standaloneSetup(scheduleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetSchedByLeaveLoc() throws Exception {
        when(scheduleService.getSchedByLeaveLoc((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/scheduleService/getSchedByLeaveLoc/{leavingLocation}", "Leaving Location");
        MockMvcBuilders.standaloneSetup(scheduleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetSchedByArrLoc() throws Exception {
        when(scheduleService.getSchedByArrLoc((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/scheduleService/getSchedByArrLoc/{arrivalLocation}", "Arrival Location");
        MockMvcBuilders.standaloneSetup(scheduleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetSchedule() throws Exception {
        when(scheduleService.getAllSchedule()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/scheduleService/getSchedule");
        MockMvcBuilders.standaloneSetup(scheduleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetScheduleByFlightName() throws Exception {
        when(scheduleService.getSchedByFlightName((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/scheduleService/getSchedByFlightName/{flightName}", "Flight Name");
        MockMvcBuilders.standaloneSetup(scheduleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetScheduleByFlightNum() throws Exception {
        when(scheduleService.getSchedByFlightNum((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/scheduleService/getSchedByFlightNum/{flightNumber}", "42");
        MockMvcBuilders.standaloneSetup(scheduleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetScheduleByLeaveAndArrLoc() throws Exception {
        when(scheduleService.getSchedByleaveLocAndArrLoc((String) any(), (String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/scheduleService/getSchedByLeaveAndArrLoc/{leavingLocation}/{arrivalLocation}", "Leaving Location",
                "Arrival Location");
        MockMvcBuilders.standaloneSetup(scheduleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

}

