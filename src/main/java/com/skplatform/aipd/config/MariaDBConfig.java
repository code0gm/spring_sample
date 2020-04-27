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
		entityManagerFactoryRef="mariaEntityManager",
		transactionManagerRef="mariaTransactionManager",
		basePackages="com.skplatform.aipd.repository.maria")
public class MariaDBConfig {
	
	@Autowired
	private Environment env;
	
	@Primary
	@Bean
	public DataSource mariaDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("maria.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("maria.datasource.url"));
		dataSource.setUsername(env.getProperty("maria.datasource.username"));
		dataSource.setPassword(env.getProperty("maria.datasource.password"));
		return dataSource;
	}
	
	@Primary
	@Bean(name="mariaEntityManager")
	public LocalContainerEntityManagerFactoryBean mariaEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(mariaDataSource())
				.packages("com.skplatform.aipd.entity.maria")
				.build();
	}
	
	@Primary
	@Bean(name="mariaTransactionManager")
	public PlatformTransactionManager mariaTransactionManager(@Qualifier("mariaEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
