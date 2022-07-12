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
 *	A security configurator to enable/disable autorization rules on API 
 *  </p>
 * @author cristiano
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http.headers().frameOptions().sameOrigin();
        	
        http.csrf().disable()
         .authorizeRequests()
         .antMatchers("*/rest/*").permitAll();

//        TODO extend security parameter
//        .antMatchers("*/rest/*").permitAll();
//        .anyRequest()
//        .authenticated().and().httpBasic();
    }
  

}