package com.riskcontrol.kg.ruleengineserver.config;

import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(Neo4jProperties.class)
public class Neo4jconfig {
    private final Neo4jProperties neo4jProperties;

    public Neo4jconfig(Neo4jProperties neo4jProperties){
        this.neo4jProperties = neo4jProperties;
    }
    @Bean
    public Driver driver(){
        Config config = Config.build().withMaxConnectionPoolSize(50)
                .withConnectionAcquisitionTimeout(5, TimeUnit.SECONDS).toConfig();
        return (Driver) GraphDatabase.driver(neo4jProperties.getUri(), AuthTokens.basic(neo4jProperties.getUsename(),
                neo4jProperties.getPassword()),config);
    }

}
