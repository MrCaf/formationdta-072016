package dta.jpa;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("dta.pizzeria")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaRepositories("dta.repository")
public class SpringJpaConfig {

	
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/pizzeria-JPA?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    // activer JPA et lire le fichier META-INF/persistence.xml
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
        emf.setPersistenceUnitName("pizzeria-unit");
        return emf;
    }
    
    // pouvoir utiliser les annotations JPA (@PersistenceContext)
    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnn() {
        return new  PersistenceAnnotationBeanPostProcessor();
    }
    
    // configuration du gestionnaire de transaction
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new JpaTransactionManager();
    }
}
