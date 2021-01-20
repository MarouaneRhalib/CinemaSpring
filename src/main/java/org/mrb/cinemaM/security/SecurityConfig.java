package org.mrb.cinemaM.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder= passwordEncoder();
		System.out.println("*********************************");
		System.out.println(passwordEncoder.encode("1234"));
		System.out.println("*********************************");
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
			.authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
			.passwordEncoder(passwordEncoder)
			.rolePrefix("ROLE_");
		 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");  
		//http.httpBasic();
		http.authorizeRequests().antMatchers("/admin**/**","/save**/**","/delete**/**","/form**/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/ville**/**").hasAnyRole("USER");
		//http.authorizeRequests().anyRequest().authenticated();
		//http.csrf();
		http.authorizeRequests().antMatchers("/user/**","/login","/webjars/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.exceptionHandling().accessDeniedPage("/notAuthorized");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/villes**/**","/cinemas**/**","/salles**/**","/imageFilm**/**","/payerTickets**/**","/seances**/**","/projections**/**");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
		
	}
}

