package com.foodbox.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.foodbox.services.UserDetailService;


@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.cors().and()
			 .csrf()
			 .disable()
			 .authorizeHttpRequests()
			 .requestMatchers("/generate-token","/user/signup","/get/product/**","/get/cuisine-type/**").permitAll()
			 .anyRequest().authenticated().and()
			 .exceptionHandling().authenticationEntryPoint(this.authEntryPoint)
			 .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
			 
		return defaultSecurityFilterChain;	 
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.userDetailService);
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("*");
		config.addAllowedHeader("Authorization");
		config.addAllowedHeader("Content-Type");
		config.addAllowedHeader("Accept");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("OPTIONS");
		config.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	

}
