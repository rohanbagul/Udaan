package com.wipro.springsecurity.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wipro.springsecurity.config.JwtService;
import com.wipro.springsecurity.entities.User;
import com.wipro.springsecurity.repository.UserRepository;
import com.wipro.springsecurity.request.AuthenticationRequest;
import com.wipro.springsecurity.request.AuthenticationResponse;
import com.wipro.springsecurity.request.RegisterRequest;
import com.wipro.springsecurity.request.UpdateRequest;
import com.wipro.springsecurity.service.AuthenticationService;
import com.wipro.springsecurity.service.UserService;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

class AuthControllerTest {
	
		
    @Test
    void testRegister() {
   
        AuthenticationService authenticationService = mock(AuthenticationService.class);
        when(authenticationService.register((RegisterRequest) any())).thenReturn(new AuthenticationResponse("ABC123"));
        AuthController authController = new AuthController(authenticationService,
                new UserService(mock(UserRepository.class), mock(RestTemplate.class)));
        ResponseEntity<AuthenticationResponse> actualRegisterResult = authController
                .register(new RegisterRequest("Jane", "Doe", "jane.doe@example.org", "iloveyou"));
        assertTrue(actualRegisterResult.hasBody());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        assertEquals(200, actualRegisterResult.getStatusCode().value());
        verify(authenticationService).register((RegisterRequest) any());
    }

  
    @Test
    void testLogin() {
       

        User user = mock(User.class);
        when(user.isLogged()).thenReturn(true);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail((String) any())).thenReturn(Optional.of(user));

        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
        authenticationProviderList.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationService service = new AuthenticationService(userRepository, passwordEncoder, new JwtService(),
                authenticationManager);

        AuthController authController = new AuthController(service,
                new UserService(mock(UserRepository.class), mock(RestTemplate.class)));
        ResponseEntity<AuthenticationResponse> actualLoginResult = authController
                .login(new AuthenticationRequest("jane.doe@example.org", "iloveyou"));
        assertTrue(actualLoginResult.hasBody());
        assertTrue(actualLoginResult.getHeaders().isEmpty());
        assertEquals(200, actualLoginResult.getStatusCode().value());
        assertEquals("You are alreday login", actualLoginResult.getBody().getToken());
        verify(userRepository).findByEmail((String) any());
        verify(user).isLogged();
    }

    
    @Test
    void testChangePassword() {
       

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save((User) any())).thenReturn(new User());
        when(userRepository.findByEmail((String) any())).thenReturn(Optional.of(new User()));

        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
        authenticationProviderList.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationService service = new AuthenticationService(userRepository, passwordEncoder, new JwtService(),
                authenticationManager);

        AuthController authController = new AuthController(service, new UserService(mock(UserRepository.class), null));
        ResponseEntity<?> actualChangePasswordResult = authController
                .changePassword(new AuthenticationRequest("jane.doe@example.org", "iloveyou"));
        assertTrue(actualChangePasswordResult.hasBody());
        assertEquals(200, actualChangePasswordResult.getStatusCodeValue());
        assertTrue(actualChangePasswordResult.getHeaders().isEmpty());
        verify(userRepository).save((User) any());
        verify(userRepository).findByEmail((String) any());
    }


    @Test
    void testGetbyid() {
      
        AuthenticationService authenticationService = mock(AuthenticationService.class);
        when(authenticationService.getUserById(anyInt())).thenReturn(new User());
        ResponseEntity<?> actualGetbyidResult = (new AuthController(authenticationService,
                new UserService(mock(UserRepository.class), mock(RestTemplate.class)))).getbyid(1);
        assertTrue(actualGetbyidResult.hasBody());
        assertEquals(200, actualGetbyidResult.getStatusCode().value());
        assertTrue(actualGetbyidResult.getHeaders().isEmpty());
        verify(authenticationService).getUserById(anyInt());
    }

    @Test
    void testDeletbyid() {
       
        AuthenticationService authenticationService = mock(AuthenticationService.class);
        when(authenticationService.deletebyid((Integer) any())).thenReturn(true);
        ResponseEntity<?> actualDeletbyidResult = (new AuthController(authenticationService,
                new UserService(mock(UserRepository.class), mock(RestTemplate.class)))).deletbyid(1);
        Object expectedHasBodyResult = actualDeletbyidResult.getBody();
        assertSame(expectedHasBodyResult, actualDeletbyidResult.hasBody());
        assertEquals(200, actualDeletbyidResult.getStatusCode().value());
        assertTrue(actualDeletbyidResult.getHeaders().isEmpty());
        verify(authenticationService).deletebyid((Integer) any());
    }


  
    @Test
    void testUpdateProfile() {
     

        User user = mock(User.class);
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFirstname((String) any());
        doNothing().when(user).setLastname((String) any());
        Optional.of(user);

        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
        authenticationProviderList.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        UserRepository userRepository = mock(UserRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationService service = new AuthenticationService(userRepository, passwordEncoder, new JwtService(),
                authenticationManager);

        UserService userService = mock(UserService.class);
        when(userService.updateUser(anyInt(), (UpdateRequest) any())).thenReturn(true);
        AuthController authController = new AuthController(service, userService);
        ResponseEntity<?> actualUpdateProfileResult = authController.updateProfile(1,
                new UpdateRequest("Jane", "Doe", "jane.doe@example.org"));
        Object expectedHasBodyResult = actualUpdateProfileResult.getBody();
        assertSame(expectedHasBodyResult, actualUpdateProfileResult.hasBody());
        assertEquals(200, actualUpdateProfileResult.getStatusCode().value());
        assertTrue(actualUpdateProfileResult.getHeaders().isEmpty());
        verify(userService).updateUser(anyInt(), (UpdateRequest) any());
    }
    
    @Test
    void testSignout() {
       
        User user = mock(User.class);
        doNothing().when(user).setLogged(anyBoolean());
        when(user.isLogged()).thenReturn(true);
        Optional.of(user);
        AuthenticationService authenticationService = mock(AuthenticationService.class);
        when(authenticationService.signout(anyInt())).thenReturn(true);
        ResponseEntity<?> actualSignoutResult = (new AuthController(authenticationService,
                new UserService(mock(UserRepository.class), mock(RestTemplate.class)))).signout(1);
        Object expectedHasBodyResult = actualSignoutResult.getBody();
        assertSame(expectedHasBodyResult, actualSignoutResult.hasBody());
        assertEquals(200, actualSignoutResult.getStatusCode().value());
        assertTrue(actualSignoutResult.getHeaders().isEmpty());
        verify(authenticationService).signout(anyInt());
    }
}

