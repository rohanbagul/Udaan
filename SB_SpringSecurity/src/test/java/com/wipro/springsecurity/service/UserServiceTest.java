package com.wipro.springsecurity.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wipro.springsecurity.entities.Booking;
import com.wipro.springsecurity.entities.User;
import com.wipro.springsecurity.repository.UserRepository;
import com.wipro.springsecurity.request.UpdateRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    void testGetUserDetails() {
        User user = new User();
        when(userRepository.findByEmail((String) any())).thenReturn(Optional.of(user));
        assertSame(user, userService.getUserDetails("jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
    }


    @Test
    void testUpdateUser() {
        when(userRepository.save((User) any())).thenReturn(new User());
        when(userRepository.findById((Integer) any())).thenReturn(Optional.of(new User()));
        assertTrue(userService.updateUser(1, new UpdateRequest("Jane", "Doe", "jane.doe@example.org")));
        verify(userRepository).save((User) any());
        verify(userRepository).findById((Integer) any());
    }

    @Test
    void testGetBookings() throws RestClientException {
        ArrayList<Object> objectList = new ArrayList<>();
        when(restTemplate.getForObject((String) any(), (Class<ArrayList<Object>>) any(), (Object[]) any()))
                .thenReturn(objectList);
        List<Booking> actualBookings = userService.getBookings(1);
        assertSame(objectList, actualBookings);
        assertTrue(actualBookings.isEmpty());
        verify(restTemplate).getForObject((String) any(), (Class<ArrayList<Object>>) any(), (Object[]) any());
    }
}

