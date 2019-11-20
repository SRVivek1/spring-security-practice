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
 * @author vivkumarsingh
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Bean
	UserDetailsManager manager() {
		return new InMemoryUserDetailsManager();
	}

	@Bean
	InitializingBean initializer(UserDetailsManager manager) {
		return () -> {

			UserDetails vivek = User.withDefaultPasswordEncoder().username("vivek").password("passw0rd")
					.roles(new String[] { "USER" }).build();

			manager.createUser(vivek);
			manager.createUser(User.withUserDetails(vivek).username("rohit").build());

		};
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		
		// Authenticate all URL patters/Requests
		http
			.authorizeRequests().anyRequest().authenticated();
		
		// Login configurer
		http
			.formLogin().loginPage("/login").permitAll();

		// Logout configurer
		http
			.logout().logoutUrl("/logout").logoutSuccessUrl("/logout-success");
		// @formatter:on

	}
}
