package com.coursecube.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({"com.coursecube.spring"})
@Import({JLCSecurityConfig.class})
public class JLCWebConfig {
	
   @Bean
	public InternalResourceViewResolver viewResolver() {
	 InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
	 viewResolver.setViewClass(JstlView.class);
	 viewResolver.setPrefix("/WEB-INF/myjsps/");
	 viewResolver.setSuffix(".jsp");
	 return viewResolver;
   }
   @Bean
   public DriverManagerDataSource dataSource() {
	   DriverManagerDataSource dataSource=new DriverManagerDataSource();
	   dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	   dataSource.setUrl("jdbc:mysql://localhost:3306/jdbc");
       dataSource.setUsername("root");
       dataSource.setPassword("mysql");
       return dataSource;
   }
   @Bean
   public JdbcTemplate jdbcTemplate(DataSource dataSource) {
	   return new JdbcTemplate(dataSource);
   }

}
