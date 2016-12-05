package net.codejava.fileupload.config;

import java.util.Properties;

import javax.sql.DataSource;

import net.codejava.fileupload.dao.FileUploadDAO;
import net.codejava.fileupload.dao.FileUploadDAOImpl;
import net.codejava.fileupload.dao.VideoUploadDAO;
import net.codejava.fileupload.dao.VideoUploadDAOImpl;
import net.codejava.fileupload.model.UploadFile;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("net.codejava.fileupload")
@EnableTransactionManagement
public class ApplicationContextConfig {
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
     
    
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl("jdbc:mysql://localhost:3306/student");
    	dataSource.setUsername("j2ee");
    	dataSource.setPassword("j2ee");
    	
    	return dataSource;
    }
    
    
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	return properties;
    }
    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	sessionBuilder.addAnnotatedClasses(UploadFile.class);
    	return sessionBuilder.buildSessionFactory();
    }
    
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}
    
    @Autowired
    @Bean(name = "fileUploadDao")
    public FileUploadDAO getUserDao(SessionFactory sessionFactory) {
    	return new FileUploadDAOImpl(sessionFactory);
    }
    
    @Autowired
    @Bean(name ="videoUploadDao")
    public VideoUploadDAO getVideoDao(SessionFactory sessionfactory){
    	return new VideoUploadDAOImpl(sessionfactory);
    }
    
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    	multipartResolver.setMaxUploadSize(52428800); // 50MB
    	multipartResolver.setMaxInMemorySize(5242880);	// 5MB
    	return multipartResolver;
    }
}
