package com.wipro.routeservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.routeservice.dto.RouteDTO;
import com.wipro.routeservice.entities.Route;
import com.wipro.routeservice.service.RouteService;

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

@ContextConfiguration(classes = {RouteController.class})
@ExtendWith(SpringExtension.class)
class RouteControllerTest {
    @Autowired
    private RouteController routeController;

    @MockBean
    private RouteService routeService;

  
    @Test
    void testAddRoute() throws Exception {
        Route route = new Route();
        route.setDestination("Destination");
        route.setId(1);
        route.setRouteCode("Route Code");
        route.setSource("Source");
        when(routeService.addRoute((RouteDTO) any())).thenReturn(route);

        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setDestination("Destination");
        routeDTO.setRouteCode("Route Code");
        routeDTO.setSource("Source");
        String content = (new ObjectMapper()).writeValueAsString(routeDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v2/addRoute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(routeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"routeCode\":\"Route Code\",\"source\":\"Source\",\"destination\":\"Destination\"}"));
    }

   
    @Test
    void testGetRoute() throws Exception {
        when(routeService.getRoute()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/getRoute");
        MockMvcBuilders.standaloneSetup(routeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

   
    @Test
    void testGetRoute2() throws Exception {
        when(routeService.getRoute()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v2/getRoute");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(routeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

   
    @Test
    void testGetRouteBySource() throws Exception {
        when(routeService.getRouteBySource((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/getRouteBySource/{source}",
                "Source");
        MockMvcBuilders.standaloneSetup(routeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

   
    @Test
    void testGetRouteBySource2() throws Exception {
        when(routeService.getRouteBySource((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v2/getRouteBySource/{source}",
                "Source");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(routeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

  
    @Test
    void testGetRouteBySourceAndDestination() throws Exception {
        Route route = new Route();
        route.setDestination("Destination");
        route.setId(1);
        route.setRouteCode("Route Code");
        route.setSource("Source");
        when(routeService.getRouteBySourceAndDestination((String) any(), (String) any())).thenReturn(route);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v2/getRouteBySrcAndDest/{source}/{destination}", "Source", "Destination");
        MockMvcBuilders.standaloneSetup(routeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"routeCode\":\"Route Code\",\"source\":\"Source\",\"destination\":\"Destination\"}"));
    }

   
    @Test
    void testDeleteById() throws Exception {
        doNothing().when(routeService).deleteById(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v2/deleteroute/{id}", 1);
        MockMvcBuilders.standaloneSetup(routeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
                
    }


    @Test
    void testDeleteById2() throws Exception {
        doNothing().when(routeService).deleteById(anyInt());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v2/deleteroute/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(routeController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
               
    }

    @Test
    void testGetRouteByCode() throws Exception {
        Route route = new Route();
        route.setDestination("Destination");
        route.setId(1);
        route.setRouteCode("Route Code");
        route.setSource("Source");
        when(routeService.getRouteBycode((String) any())).thenReturn(route);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/getRouteByCode/{routeCode}",
                "Route Code");
        MockMvcBuilders.standaloneSetup(routeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"routeCode\":\"Route Code\",\"source\":\"Source\",\"destination\":\"Destination\"}"));
    }
}

