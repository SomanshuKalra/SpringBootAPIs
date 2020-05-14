/**
 * Configuration class for web security
 */
package com.somanshu.spring.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Description: Class for defining web security. Specifies secure protocol
 * 				and authorizes all requests.  
 * @author 		Somanshu Kalra
 * Date: 		13/05/2020
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.requiresChannel()
		.anyRequest()
		.requiresSecure()
		.and()
		.authorizeRequests()
		.antMatchers("/**")
		.permitAll();
	}
}
