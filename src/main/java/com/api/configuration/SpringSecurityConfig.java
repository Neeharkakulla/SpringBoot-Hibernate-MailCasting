package com.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "com.api.service")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePwd());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	http
		.authorizeRequests()
		.antMatchers("/register","/register-page")
		.permitAll()
	.and()
		.antMatcher("/**")
	    .authorizeRequests()
	    .anyRequest()
	    .authenticated()
    .and()
        .formLogin()
        .loginPage("/index")
        .loginProcessingUrl("/login")
        .usernameParameter("email")
        .passwordParameter("password")
        .failureUrl("/index?error=true")
        .defaultSuccessUrl("/home")
        .permitAll()
     .and()
        .logout()
        .logoutSuccessUrl("/index")
        .invalidateHttpSession(true)
        .permitAll()
    .and()
        .csrf()
        .disable();

	}

	@Bean
	public BCryptPasswordEncoder encodePwd() {
		
		return new BCryptPasswordEncoder();
	}
	
	public AuthenticationProvider authProvider() {	
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(encodePwd());
		return provider;
	}
}
