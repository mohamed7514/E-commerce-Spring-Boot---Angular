package com.khalil.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	
	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// protect endpoint /api/orders
		http.authorizeRequests()
			.antMatchers("/api/orders/**")
			.authenticated()
			.and()
			.oauth2ResourceServer()
			.jwt();
		
		// add CORS filters
		http.cors();
		
		Okta.configureResourceServer401ResponseBody(http);
		
		// disable CSRF since we are not using cookies for session tracking
		http.csrf().disable();
	}
	

}
