package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	private final CustomUserDetailService customUserDetailService;
	
	public SecurityConfig(CustomUserDetailService customUserDetailService) {
		this.customUserDetailService =customUserDetailService;
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authz -> authz
				  .requestMatchers("/rlogin","/rsignup","/farmer","/fsignup","/asignup","/home1","/contact1","/about1","/admin","/admindash","/ft","/rt").permitAll()
				  .requestMatchers("/").permitAll()
				  
//				  .requestMatchers("/h2/**").permitAll()
				  .anyRequest().authenticated())
				  .formLogin(formLogin -> formLogin.loginPage("/rlogin")
                .loginProcessingUrl("/rlogin")
                .usernameParameter("email").passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/redirectTo", true));
		
//                .failureUrl("/login?error=true")
              
                http.authenticationProvider(authenticationProvider());
//                http.sessionManagement(management -> management
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
                return http.build()
                ;	
	}
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/images/**", "/js/**", "/css/**");
    }
                
               
                
                
		@Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

	        authenticationProvider.setUserDetailsService(customUserDetailService);
	        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

	        return authenticationProvider;
	    } 
               
        

       
    }

