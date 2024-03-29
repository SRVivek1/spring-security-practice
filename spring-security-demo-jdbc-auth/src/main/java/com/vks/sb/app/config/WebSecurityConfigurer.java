/**
 * 
 */
package com.vks.sb.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * @author vivek
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	/**
	 * Configure authentication manager.
	 * 
	 * @return
	 */
	@Bean
	UserDetailsManager manager(DataSource ds) {
		return new JdbcUserDetailsManager(ds);
	}

	@Bean
	public InitializingBean initializer(UserDetailsManager manager) {
		return () -> {
			UserDetails vivek = User.withDefaultPasswordEncoder().username("vivek").password("passw0rd").roles(new String[] {"USER", "ADMIN"})
					.build();

			manager.createUser(vivek);
		};
	}

	/**
	 * Define authentication mechanism and restricted resources.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// http basic auth for all
		http.httpBasic();

		// All url to be authenticated.
		http.authorizeRequests().anyRequest().authenticated();
	}

}
