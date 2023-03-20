package com.wipro.routeservice.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wipro.routeservice.dto.RouteDTO;
import com.wipro.routeservice.entities.Route;
import com.wipro.routeservice.exception.CustomException;
import com.wipro.routeservice.exception.NotFoundException;
import com.wipro.routeservice.repository.RouteRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RouteServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RouteServiceImplTest {
    @MockBean
    private RouteRepository routeRepository;

    @Autowired
    private RouteServiceImpl routeServiceImpl;


    @Test
    void testAddRoute() {
        RouteDTO newRoute = new RouteDTO("routeCode", "source", "destination");
        Route savedRoute = new Route();
        savedRoute.setRouteCode("routeCode");
        savedRoute.setSource("source");
        savedRoute.setDestination("destination");

        when(routeRepository.findByRouteCode("routeCode")).thenReturn(null);
        when(routeRepository.save(Mockito.any(Route.class))).thenReturn(savedRoute);

        // Act
        Route createdRoute = routeServiceImpl.addRoute(newRoute);

        // Assert
        Assertions.assertEquals("routeCode", createdRoute.getRouteCode());
        Assertions.assertEquals("source", createdRoute.getSource());
        Assertions.assertEquals("destination", createdRoute.getDestination());
    }

    @Test
    void testAddRoute3() {
        RouteDTO existingRoute = new RouteDTO("routeCode", "source", "destination");
        Route foundRoute = new Route();
        foundRoute.setRouteCode("routeCode");
        foundRoute.setSource("source");
        foundRoute.setDestination("destination");

        when(routeRepository.findByRouteCode("routeCode")).thenReturn(foundRoute);


        Assertions.assertThrows(CustomException.class, () -> {
            routeServiceImpl.addRoute(existingRoute);
        });
    }


    @Test
    void testGetRoute() {
        ArrayList<Route> routeList = new ArrayList<>();
        when(routeRepository.findAll()).thenReturn(routeList);
        List<Route> actualRoute = routeServiceImpl.getRoute();
        assertSame(routeList, actualRoute);
        assertTrue(actualRoute.isEmpty());
        verify(routeRepository).findAll();
    }

    @Test
    void testGetRoute2() {
        when(routeRepository.findAll()).thenThrow(new CustomException("An error occurred"));
        assertThrows(CustomException.class, () -> routeServiceImpl.getRoute());
        verify(routeRepository).findAll();
    }


    @Test
    void testGetRouteBycode() {
        Route route = new Route();
        route.setDestination("Destination");
        route.setId(1);
        route.setRouteCode("Route Code");
        route.setSource("Source");

        when(routeRepository.findByRouteCode((String) any())).thenReturn(route);
        assertSame(route, routeServiceImpl.getRouteBycode("Route Code"));
        verify(routeRepository).findByRouteCode((String) any());
    }


    @Test
    void testGetRouteBycode2() {

        when(routeRepository.findByRouteCode((String) any())).thenThrow(new CustomException("An error occurred"));
        assertThrows(CustomException.class, () -> routeServiceImpl.getRouteBycode("Route Code"));
        verify(routeRepository).findByRouteCode((String) any());
    }


    @Test
    void testGetRouteBySourceAndDestination() {
        Route route = new Route();
        route.setDestination("Destination");
        route.setId(1);
        route.setRouteCode("Route Code");
        route.setSource("Source");
        when(routeRepository.findBySourceAndDestination((String) any(), (String) any())).thenReturn(route);
        assertSame(route, routeServiceImpl.getRouteBySourceAndDestination("Source", "Route"));
        verify(routeRepository).findBySourceAndDestination((String) any(), (String) any());
    }


    @Test
    void testGetRouteBySourceAndDestination2() {
        when(routeRepository.findBySourceAndDestination((String) any(), (String) any()))
                .thenThrow(new CustomException("An error occurred"));
        assertThrows(CustomException.class, () -> routeServiceImpl.getRouteBySourceAndDestination("Source", "Route"));
        verify(routeRepository).findBySourceAndDestination((String) any(), (String) any());
    }


    @Test
    void testGetRouteBySource() {
        ArrayList<Route> routeList = new ArrayList<>();
        when(routeRepository.findBySource((String) any())).thenReturn(routeList);
        List<Route> actualRouteBySource = routeServiceImpl.getRouteBySource("Source");
        assertSame(routeList, actualRouteBySource);
        assertTrue(actualRouteBySource.isEmpty());
        verify(routeRepository).findBySource((String) any());
    }


    @Test
    void testGetRouteBySource2() {
        when(routeRepository.findBySource((String) any())).thenThrow(new CustomException("An error occurred"));
        assertThrows(CustomException.class, () -> routeServiceImpl.getRouteBySource("Source"));
        verify(routeRepository).findBySource((String) any());
    }


    @Test
    void testDeleteById() {
        Route route = new Route();
        route.setDestination("Destination");
        route.setId(1);
        route.setRouteCode("Route Code");
        route.setSource("Source");
        Optional<Route> ofResult = Optional.of(route);
        doNothing().when(routeRepository).deleteById((Integer) any());
        when(routeRepository.findById((Integer) any())).thenReturn(ofResult);
        routeServiceImpl.deleteById(1);
        verify(routeRepository).findById((Integer) any());
        verify(routeRepository).deleteById((Integer) any());
        assertTrue(routeServiceImpl.getRoute().isEmpty());
    }


    @Test
    void testDeleteById2() {
        Route route = new Route();
        route.setDestination("Destination");
        route.setId(1);
        route.setRouteCode("Route Code");
        route.setSource("Source");
        Optional<Route> ofResult = Optional.of(route);
        doThrow(new CustomException("An error occurred")).when(routeRepository).deleteById((Integer) any());
        when(routeRepository.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(CustomException.class, () -> routeServiceImpl.deleteById(1));
        verify(routeRepository).findById((Integer) any());
        verify(routeRepository).deleteById((Integer) any());
    }



    @Test
    void testDeleteById4() {
        doNothing().when(routeRepository).deleteById((Integer) any());
        when(routeRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> routeServiceImpl.deleteById(1));
        verify(routeRepository).findById((Integer) any());
    }
}

