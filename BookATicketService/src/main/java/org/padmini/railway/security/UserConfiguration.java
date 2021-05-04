package org.padmini.railway.security;
import org.springframework.context.annotation.Bean; 
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; 
import org.springframework.security.crypto.password.NoOpPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder;
  
@SuppressWarnings("deprecation")
@EnableWebSecurity 
public class UserConfiguration extends WebSecurityConfigurerAdapter 
{
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception  { 
	  auth.inMemoryAuthentication()
	  		.withUser("padmini").password("padminiuser").roles("USER"); 
  }
  
  @Override 
  protected void configure(HttpSecurity http) throws Exception  {
	  
	  http.httpBasic()
	  .and().authorizeRequests()
	  	  .antMatchers("/user/add","/user/**").hasRole("USER")
	  	  //.antMatchers("/user/all").permitAll()
	  	  .and().csrf().disable().headers().frameOptions().disable();
	  	  //.formLogin(); 
	 }
  
  @Bean
  public PasswordEncoder getPasswordEncoder() 
  { 
	  return NoOpPasswordEncoder.getInstance(); 
  } 
}
 