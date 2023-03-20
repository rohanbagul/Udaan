package com.wipro.springsecurity.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wipro.springsecurity.config.JwtService;
import com.wipro.springsecurity.entities.Role;
import com.wipro.springsecurity.entities.User;
import com.wipro.springsecurity.exception.CustomException;
import com.wipro.springsecurity.repository.UserRepository;
import com.wipro.springsecurity.request.AuthenticationRequest;
import com.wipro.springsecurity.request.RegisterRequest;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationService.class})
@ExtendWith(SpringExtension.class)
class AuthenticationServiceTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

 
    @Test
    void testRegister() {
        when(userRepository.save((User) any())).thenReturn(new User());
        when(userRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        when(jwtService.generateToken((UserDetails) any())).thenThrow(new CustomException("An error occurred"));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertThrows(CustomException.class,
                () -> authenticationService.register(new RegisterRequest("Jane", "Doe", "jane.doe@example.org", "iloveyou")));
        verify(userRepository).save((User) any());
        verify(userRepository).findByEmail((String) any());
        verify(jwtService).generateToken((UserDetails) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    @Test
    void testAuthenticate() throws AuthenticationException {
        User user = mock(User.class);
        when(user.isLogged()).thenThrow(new CustomException("An error occurred"));
        doThrow(new CustomException("An error occurred")).when(user).setLogged(anyBoolean());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.save((User) any())).thenReturn(new User());
        when(userRepository.findByEmail((String) any())).thenReturn(ofResult);
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertThrows(CustomException.class,
                () -> authenticationService.authenticate(new AuthenticationRequest("jane.doe@example.org", "iloveyou")));
        verify(userRepository).findByEmail((String) any());
        verify(user).isLogged();
    }

  
    @Test
    void testChangePassword3() {
        User user = mock(User.class);
        doNothing().when(user).setPassword((String) any());
        Optional<User> ofResult = Optional.of(user);
        User user1 = new User();
        when(userRepository.save((User) any())).thenReturn(user1);
        when(userRepository.findByEmail((String) any())).thenReturn(ofResult);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertSame(user1,
                authenticationService.changePassword(new AuthenticationRequest("jane.doe@example.org", "iloveyou")));
        verify(userRepository).save((User) any());
        verify(userRepository).findByEmail((String) any());
        verify(user).setPassword((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    @Test
    void testChangePassword6() {
        User user = mock(User.class);
        doThrow(new CustomException("An error occurred")).when(user).setPassword((String) any());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.save((User) any())).thenReturn(new User());
        when(userRepository.findByEmail((String) any())).thenReturn(ofResult);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertThrows(CustomException.class,
                () -> authenticationService.changePassword(new AuthenticationRequest("jane.doe@example.org", "iloveyou")));
        verify(userRepository).findByEmail((String) any());
        verify(user).setPassword((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    
    @Test
    void testGetUserById() {
        User user = new User();
        when(userRepository.findById((Integer) any())).thenReturn(Optional.of(user));
        assertSame(user, authenticationService.getUserById(1));
        verify(userRepository).findById((Integer) any());
    }

    
    @Test
    void testGetUserById2() {
        when(userRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> authenticationService.getUserById(1));
        verify(userRepository).findById((Integer) any());
    }

  
   
    @Test
    void testDeletebyid() {
        doNothing().when(userRepository).deleteById((Integer) any());
        assertTrue(authenticationService.deletebyid(1));
        verify(userRepository).deleteById((Integer) any());
    }

    
    @Test
    void testDeletebyid2() {
        doThrow(new CustomException("An error occurred")).when(userRepository).deleteById((Integer) any());
        assertThrows(CustomException.class, () -> authenticationService.deletebyid(1));
        verify(userRepository).deleteById((Integer) any());
    }

    
    @Test
    void testSignout() {
        User user = mock(User.class);
        doNothing().when(user).setLogged(anyBoolean());
        when(user.isLogged()).thenReturn(true);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.save((User) any())).thenReturn(new User());
        when(userRepository.findById((Integer) any())).thenReturn(ofResult);
        assertTrue(authenticationService.signout(1));
        verify(userRepository).save((User) any());
        verify(userRepository).findById((Integer) any());
        verify(user).isLogged();
        verify(user).setLogged(anyBoolean());
    }

  
    @Test
    void testSignout2() {
        User user = mock(User.class);
        doThrow(new CustomException("An error occurred")).when(user).setLogged(anyBoolean());
        when(user.isLogged()).thenReturn(true);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.save((User) any())).thenReturn(new User());
        when(userRepository.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(CustomException.class, () -> authenticationService.signout(1));
        verify(userRepository).findById((Integer) any());
        verify(user).isLogged();
        verify(user).setLogged(anyBoolean());
    }

   

}

