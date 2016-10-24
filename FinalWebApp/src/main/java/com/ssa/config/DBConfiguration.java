package com.ssa.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ssa.entity.*;

@Configuration
@EnableTransactionManagement
public class DBConfiguration {

	@Bean
    public HibernateTemplate hibernateTemplate() {
        return new HibernateTemplate(sessionFactory());
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/final_project?autoReconnect=true&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("lacrosse593");
        return dataSource;
    }
    
    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(getDataSource()).addAnnotatedClasses(
        		Position.class,Team.class,OffensivePlayer.class,OffensiveStat.class,
        		DefensiveStat.class,Matchup.class,GameResult.class)
                .buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager hibTransMan() {
        return new HibernateTransactionManager(sessionFactory());
    }
}
