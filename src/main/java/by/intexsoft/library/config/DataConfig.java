package by.intexsoft.library.config;

import static org.springframework.orm.jpa.vendor.Database.POSTGRESQL;

import java.util.Properties;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Data source configuration
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("by.intexsoft.library.service")
@PropertySource("classpath:app.properties")
@EnableJpaRepositories("by.intexsoft.library.repository")
public class DataConfig {

	@Value("${db.driver}")
	private String driverClassName;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;
	@Value("${db.hibernate.show_sql}")
	private boolean show_sql;
	@Value("${db.hibernate.hbm2ddl.auto}")
	private String hbm2ddl;

	/**
	 * Connection settings
	 * 
	 * @return DataSource
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	/**
	 * Flyway configuration
	 * 
	 * @return {@link Flyway}
	 */
	@Bean(initMethod = "migrate")
	Flyway flyway() {
		Flyway flyway = new Flyway();
		flyway.setBaselineOnMigrate(true);
		flyway.setLocations("classpath:/db/migration/");
		flyway.setDataSource(dataSource());
		return flyway;
	}

	/**
	 * Manager for entities
	 * 
	 * @return LocalContainerEntityManagerFactoryBean
	 */
	@Bean
	@DependsOn("flyway")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("by.intexsoft.library.model");
		entityManagerFactoryBean.setJpaProperties(jpaProperties());
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		return entityManagerFactoryBean;
	}

	/**
	 * JPA provider
	 * 
	 * @return JpaVendorAdapter
	 */
	@Bean
	public JpaVendorAdapter adapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(POSTGRESQL);
		adapter.setShowSql(show_sql);
		return adapter;
	}

	/**
	 * Transaction support
	 * 
	 * @return PlatformTransactionManager
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	private Properties jpaProperties() {
		Properties properties = new Properties();
		// properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
		return properties;
	}
}
