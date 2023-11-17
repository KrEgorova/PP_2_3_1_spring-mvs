package web.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("web")
@EnableTransactionManagement
@PropertySource(value = "classpath:db.properties")
public class DatabaseConfig {

    private Environment environment;

    @Autowired
    public DatabaseConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean("entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setDataSource(dataSource());
        entityManager.setPersistenceUnitName(HibernatePersistenceProvider.class.getName());
        entityManager.setPackagesToScan(environment.getRequiredProperty("db.entity.package"));
        entityManager.setJpaProperties(hibernateProperties());
        return entityManager;
    }

    @Bean("dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getRequiredProperty("db.url"));
        dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        dataSource.setUsername(environment.getRequiredProperty("db.username"));
        dataSource.setPassword(environment.getRequiredProperty("db.password"));
        return dataSource;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean(name = "transactionManager")
    public TransactionManager txManager() {
        return new JpaTransactionManager();
    }
}
