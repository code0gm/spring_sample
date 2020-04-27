package com.skplatform.aipd.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef="primaryEntityManager",
		transactionManagerRef="primaryTransactionManager",
		basePackages="com.skplatform.aipd.repository.primary")
public class PrimaryDBConfig {
	
	@Autowired
	private Environment env;
	
	@Primary
	@Bean
	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("primary.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("primary.datasource.url"));
		dataSource.setUsername(env.getProperty("primary.datasource.username"));
		dataSource.setPassword(env.getProperty("primary.datasource.password"));
		return dataSource;
	}
	
	@Primary
	@Bean(name="primaryEntityManager")
	public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(mysqlDataSource())
				.packages("com.skplatform.aipd.entity")
				.build();
	}
	
	@Primary
	@Bean(name="primaryTransactionManager")
	public PlatformTransactionManager mysqlTransactionManager(@Qualifier("primaryEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
