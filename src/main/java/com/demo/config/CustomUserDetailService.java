package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.Repository.RetailerRepository;
import com.demo.Repository.UserRepository;

@Service

public class CustomUserDetailService implements UserDetailsService{

	
	private final UserRepository userRepo;
	public CustomUserDetailService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return (UserDetails) userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
	}
	
}
