package com.amaris.hometest.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>
 * Task 4 (optional)
 * This task is _voluntarily_, if you can't get enough of hacking tech challenges, implement security. 
 * Secure the API so that authentication is needed to access it. The details are up to you.
 * 
 * SEE API CREDENTIANL IN APPLICATION.PROPERTIES FILE
 * <p/>
 * 
 * @author cristiano
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http
         .csrf().disable()
         .authorizeRequests()
         .antMatchers("*/rest/*").permitAll();
//        TODO 
//        .anyRequest()
//        .authenticated().and().httpBasic();
    }
  

}