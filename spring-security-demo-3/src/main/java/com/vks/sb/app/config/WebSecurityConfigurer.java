/**
 * 
 */
package com.vks.sb.app.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * @author vivek
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	/**
	 * In-memory user details manager.
	 */
	@Bean
	public UserDetailsManager memory() {
		return new InMemoryUserDetailsManager();
	}

	/**
	 * Initialize users for In-Memory authentication.
	 * 
	 * @param manager
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@Bean
	public InitializingBean initializer(UserDetailsManager manager) {
		
		// Initialize users
		return () -> {

			UserDetails vivek = User.withDefaultPasswordEncoder().username("vivek").password("passw0rd").roles("USER")
					.build();
			manager.createUser(vivek);

			manager.createUser(User.withUserDetails(vivek).username("rohit").build());
			manager.createUser(User.withUserDetails(vivek).username("mohit").build());
		};
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Authentication type HTTP Basic
		http
			.httpBasic();

		// Authenticate all requests
		http
			.authorizeRequests().anyRequest().authenticated();
	}

}
