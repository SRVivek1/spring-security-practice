/**
 * 
 */
package com.vks.sb.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

/**
 * @author vivek
 *
 */
@Configuration
@EnableWebSecurity
public class LdapWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// @formatter:off

		auth
			.ldapAuthentication()
				.userDnPatterns("uid={0},ou=people")
			.groupSearchBase("ou=groups")
			.contextSource()
				.url("ldap://127.0.0.1:8389/dc=example,dc=org")
			.and()
			.passwordCompare()
				.passwordAttribute("userpassword")
				.passwordEncoder(new LdapShaPasswordEncoder());
		// @formatter:on
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
