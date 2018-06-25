package com.bridgeit.configuration;

import static org.hibernate.cfg.AvailableSettings.C3P0_ACQUIRE_INCREMENT;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_STATEMENTS;
import static org.hibernate.cfg.AvailableSettings.C3P0_MIN_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_TIMEOUT;
import static org.hibernate.cfg.AvailableSettings.DIALECT;
import static org.hibernate.cfg.AvailableSettings.DRIVER;
import static org.hibernate.cfg.AvailableSettings.FORMAT_SQL;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.PASS;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.AvailableSettings.URL;
import static org.hibernate.cfg.AvailableSettings.USER;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScans(value = {
	      @ComponentScan("com.bridgeit") })


public class BeanConfiguration {

	@Autowired
	private Environment environment;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
	 LocalSessionFactoryBean factoryBean=new LocalSessionFactoryBean();	
		Properties properties=new Properties();
		properties.put(DRIVER,environment.getProperty("mysql.driverClassName"));
		properties.put(URL,environment.getProperty("mysql.url"));
		properties.put(USER, environment.getProperty("mysql.username"));
		properties.put(PASS, environment.getProperty("mysql.password"));
		
		properties.put(DIALECT, environment.getProperty("hibernate.dialect"));
		properties.put(SHOW_SQL,environment.getProperty("hibernate.show_sql"));
		properties.put(FORMAT_SQL, environment.getProperty("hibernate.format_sql"));
		properties.put(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));
		
		properties.put(C3P0_MIN_SIZE, environment.getProperty("hibernate.C3P0_MIN_SIZE"));
		properties.put(C3P0_MAX_SIZE, environment.getProperty("hibernate.C3P0_MAX_SIZE"));
		properties.put(C3P0_ACQUIRE_INCREMENT,environment.getProperty("hibernate.C3P0_ACQUIRE_INCREMENT"));
		properties.put(C3P0_TIMEOUT, environment.getProperty("hibernate.C3P0_TIMEOUT"));
		properties.put(C3P0_MAX_STATEMENTS, environment.getProperty("hibernate.C3P0_MAX_STATEMENTS"));
			
		factoryBean.setHibernateProperties(properties);
		factoryBean.setPackagesToScan("com.bridgeit.model");
		return factoryBean;
		
	}
	
	@Bean
	public HibernateTransactionManager getTransaction()
	{
	 HibernateTransactionManager transactionManager=new HibernateTransactionManager();
	 transactionManager.setSessionFactory(sessionFactory().getObject());
	 return transactionManager;
	}
	
}
