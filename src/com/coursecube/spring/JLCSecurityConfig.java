package com.coursecube.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
public class JLCSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
	DataSource dataSource; 
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		String usersQuery="select username,password,active from myusers where username=?";
		String rolesQuery="select username,role from myroles where username=?";
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(usersQuery)
		.authoritiesByUsernameQuery(rolesQuery)
		.passwordEncoder(bCryptPasswordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/deleteBook**").access("hasRole('ROLE_ADIM')")
		.antMatchers("/addBook").access("hasAnyRole('ROLE_ADMIN','ROLE_STOREKEEPER')")
		.antMatchers("/editBook").access("hasAnyRole('ROLE_ADMIN','ROLE_STOREKEEPER')")
		.antMatchers("/placeOrder").access("hasAnyRole('ROLE_CUSTOMER')")
		.and()
		.formLogin().loginPage("/login").failureUrl("/login?error")
		.usernameParameter("username").passwordParameter("password")
		.and()
		.logout().invalidateHttpSession(true)
		.logoutSuccessUrl("login?logout")
		.and()
		.exceptionHandling().accessDeniedPage("/WEB-INF/myjsps/invalidAccess.jsp")
		.and()
		.csrf()
		.and()
		.sessionManagement().enableSessionUrlRewriting(true)
		.maximumSessions(1);
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
    
    
 }
