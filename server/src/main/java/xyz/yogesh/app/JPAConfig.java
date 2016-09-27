package xyz.yogesh.app;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.logging.SessionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/resources/netfilms-api.properties")
public class JPAConfig {
	
	@Autowired
	Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("xyz.yogesh.app.entity");
		emf.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());
		emf.setJpaProperties(jpaProperties());
		return emf;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty("netfilms-api.driver"));
		ds.setUrl(env.getProperty("netfilms-api.url"));
		ds.setUsername(env.getProperty("netfilms-api.username"));
		ds.setPassword(env.getProperty("netfilms-api.password"));
		return ds;
	}

	@Bean
	public PlatformTransactionManager txnManager(EntityManagerFactory emf) {
		JpaTransactionManager txnManager = new JpaTransactionManager(emf);
		return txnManager;
	}

	private Properties jpaProperties() {
		Properties props = new Properties();
		props.setProperty(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_ONLY);
		props.setProperty(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINE_LABEL);
		props.setProperty(PersistenceUnitProperties.WEAVING, "false");
		props.setProperty(PersistenceUnitProperties.VALIDATION_MODE, "AUTO");
		return props;
	}
}