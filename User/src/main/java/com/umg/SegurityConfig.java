package com.umg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.umg.service.UserService;

@Configuration
@EnableWebSecurity
public class SegurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private UserService userDetail;
	
	@Autowired
	private BCryptPasswordEncoder bcryt;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		return bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		/*try {
			auth.inMemoryAuthentication()
			.withUser("user")
			.password("password")
			.roles("USER")
			.and()
			.withUser("admin")
			.password("password")
			.roles("USER", "ADMIN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		try {
			auth.userDetailsService(userDetail).passwordEncoder(bcryt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void configure(HttpSecurity http) {
		try {
			//autentifica la sesion al autentificar
			http.sessionManagement()
			 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			 .antMatchers("/api/user/**")
			.authenticated()
			.anyRequest()
			.authenticated()
			 .and()
			.httpBasic()
			 .realmName("User Registration System")
			 .and()
			.csrf()
			 .disable();
			/*
			//config 1
			http.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
			 //config 2
			 http.sessionManagement()
			 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			 .antMatchers("/api/user/**")
			.authenticated()
			.anyRequest()
			.authenticated()
			 .and()
			.httpBasic()
			 .realmName("User Registration System")
			 .and()
			.csrf()
			 .disable();
			 //config 3
			 http
			 .httpBasic().and()
			 .authorizeRequests()
			 .antMatchers(HttpMethod.GET, "/api/user/")
			 .hasRole("USER")
			 .antMatchers(HttpMethod.POST, "/api/user/")
			 .hasRole("USER")
			 .antMatchers(HttpMethod.PUT, "/api/user/**")
			.hasRole("USER")
			 .antMatchers(HttpMethod.DELETE, "/api/user/**")
			 .hasRole("ADMIN")
			 .and()
			 .csrf()
			 .disable();*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
