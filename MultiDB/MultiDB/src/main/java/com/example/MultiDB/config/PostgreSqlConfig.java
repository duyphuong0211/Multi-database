package com.example.MultiDB.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "postgresqlEntityManagerFactory",
        transactionManagerRef = "postgresqlTransactionManager",
        basePackages = "com.example.MultiDB.database.postgresql.repository")

public class PostgreSqlConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.postgresql.datasource")
    public DataSourceProperties postgresqlDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    public DataSource postgresqlDataSource(@Qualifier("postgresqlDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "postgresqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(@Qualifier("postgresqlDataSource") DataSource postgresqlDataSource, EntityManagerFactoryBuilder builder) {

        return builder.dataSource(postgresqlDataSource)
                .packages("com.example.MultiDB.database.postgresql.domain")
                .persistenceUnit("postgresql")
                .build();

    }

    @Bean
    public PlatformTransactionManager postgresqlTransactionManager(@Qualifier("postgresqlEntityManagerFactory")
                                                                           EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
