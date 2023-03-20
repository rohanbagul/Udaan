package com.wipro.springsecurity.service;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wipro.springsecurity.config.JwtService;
import com.wipro.springsecurity.entities.Role;
import com.wipro.springsecurity.entities.User;
import com.wipro.springsecurity.exception.CustomException;
import com.wipro.springsecurity.exception.NotFoundException;
import com.wipro.springsecurity.repository.UserRepository;
import com.wipro.springsecurity.request.AuthenticationRequest;
import com.wipro.springsecurity.request.AuthenticationResponse;
import com.wipro.springsecurity.request.RegisterRequest;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(RegisterRequest request) {
		
		var user = User.builder()
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		
		Optional<User> getUser = userRepository.findByEmail(request.getEmail());
		
		if(getUser.isEmpty()) {
			userRepository.save(user);
			var jwtToken = jwtService.generateToken(user);
			return AuthenticationResponse.builder()
					.token(jwtToken)
					.build();
		}
		else {
			throw new CustomException("User not registered. Email exist");
		}	
		
		
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		
		var user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new CustomException("User not found with email :"+ request.getEmail()));
		
		if(user.isLogged()==false) {
			
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(),
							request.getPassword()
							)
					);
				
			user.setLogged(true);
			userRepository.save(user);

			
			Timer timer = new Timer();
			timer.schedule(new TimerTask(){
			@Override
			public void run() {
				
				user.setLogged(false);
				userRepository.save(user);

				}
			}, 1000 * 60 *120);
			
			var jwtToken  = jwtService.generateToken(user);
			
			return AuthenticationResponse.builder()
					.token(jwtToken)
					.build();
		}
		return AuthenticationResponse.builder()
				.token("You are alreday login")
				.build();
	}
	
	
	public User changePassword(AuthenticationRequest request) {
		
		  Optional<User> user2 =userRepository.findByEmail(request.getEmail());
		  User user = user2.get();
		  
		  if(user2.isPresent()) {
			  
			  user.setPassword(passwordEncoder.encode(request.getPassword()));  
			  
		  }
		  else {
			  throw new CustomException("User not found with email :"+ request.getEmail());
		  }
		return userRepository.save(user);
	}

	public User getUserById(int id) {
		
		return userRepository.findById(id).orElseThrow(()-> new CustomException("User not found !!"));
	}

	public boolean deletebyid(Integer id) {
		userRepository.deleteById(id);
		return true;
	}
	
	public boolean signout(int id){
		
		Optional<User> user = userRepository.findById(id);
		User u1 = user.get();
		if(u1 != null) {
			if(u1.isLogged()) {
				u1.setLogged(false);
				userRepository.save(u1);
				return true;
			}
			
		}
		return false;
		
	}
		
}
