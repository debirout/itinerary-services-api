package com.rout.travels.itinerary;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.rout.travels.itinerary.dao.repository.SegmentTRepository;
import com.rout.travels.itinerary.model.SegmentT;

@Configuration
@EnableJpaRepositories
public class JpaConfiguration {
	
	@Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(  JpaVendorAdapter adapter, DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPackagesToScan(SegmentT.class.getPackage().getName());
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(adapter);
       /* emf.setJpaDialect(adapter.getJpaDialect());*/
        return emf;
    }

    @Bean
    PlatformTransactionManager transactionManager( EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
