package com.api.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.api")
@PropertySource(value="classpath:application.properties")
public class HibernateConfig {
	
	
	
	@Autowired
    Environment environment;
 
    //--------------------
    private final String PROPERTY_DRIVER = "spring.datasource.driver-class-name";
	private final String PROPERTY_URL = "spring.datasource.url";
	private final String PROPERTY_USERNAME = "spring.datasource.user";
	private final String PROPERTY_PASSWORD = "spring.datasource.password";
	private final String PROPERTY_SHOW_SQL = "hibernate.show_sql";
	private final String PROPERTY_DIALECT = "hibernate.dialect";
	private final String PROPERTY_DDL_AUTO="hibernate.hbm2ddl.auto";
	private final String PROPERTY_FORMAT_SQL="hibernate.format_sql";


	@Bean
	DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(environment.getProperty(PROPERTY_URL));
		ds.setUsername(environment.getProperty(PROPERTY_USERNAME));
		ds.setPassword(environment.getProperty(PROPERTY_PASSWORD));
		ds.setDriverClassName(environment.getProperty(PROPERTY_DRIVER));
		return ds;
	}

	Properties hibernateProps() {
		 Properties properties = new Properties();
	        properties.put(PROPERTY_DIALECT, environment.getRequiredProperty("hibernate.dialect"));
	        properties.put(PROPERTY_SHOW_SQL, environment.getRequiredProperty("hibernate.show_sql"));
	        properties.put(PROPERTY_FORMAT_SQL, environment.getRequiredProperty("hibernate.format_sql"));
	        properties.put(PROPERTY_DDL_AUTO, environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
	        return properties;
	}

		@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	
		LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
		lfb.setDataSource(dataSource());
		
		lfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		lfb.setPackagesToScan("com.api.model");	
		lfb.setJpaProperties(hibernateProps());
		return lfb;
	}
	
}