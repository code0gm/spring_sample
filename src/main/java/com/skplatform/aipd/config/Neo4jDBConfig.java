package com.skplatform.aipd.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(
    sessionFactoryRef = "getSessionFactory",
    transactionManagerRef = "graphTransactionManager",
    basePackages = "com.skplatform.aipd.repository.neo4j")
public class Neo4jDBConfig {
	
	@Autowired 
	private Environment env;
	
    @Bean(name = "getSessionFactory")
    public SessionFactory graphSessionFactory() {
        return new SessionFactory(configuration(),"com.skplatform.aipd.entity.neo4j");
    }

    @Bean(name = "graphTransactionManager")
    public Neo4jTransactionManager graphTransactionManager(
	@Qualifier("getSessionFactory") 
	SessionFactory sessionFactory) {
        return new Neo4jTransactionManager(sessionFactory);
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
		Neo4jProperties properties = new Neo4jProperties();
		properties.setUri(env.getProperty("neo4j.datasource.url"));
		properties.setUsername(env.getProperty("neo4j.datasource.username"));
		properties.setPassword(env.getProperty("neo4j.datasource.password"));
    	return properties.createConfiguration();
    }
}