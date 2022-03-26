package cz.sda.store.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "cz.sda.store", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
@ComponentScan(basePackages = "cz.sda.store")
@EnableWebMvc
public class StoreConfig implements ApplicationContextAware, WebMvcConfigurer {

    @Setter(onMethod_ = {@Autowired})
    private ApplicationContext applicationContext;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    @Bean
    @Description("Thymeleaf Template Resolver")
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    @Bean
    @Description("Thymeleaf Template Engine")
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(this.templateEngine());
        return viewResolver;
    }

    @Bean
    @Description("Customer configuration for uploading files")
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(10000);
        return resolver;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(StorePropertiesConfig propertiesConfig) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan("cz.sda.store");
        em.setPersistenceUnitName("default");
        em.setDataSource(dataSource(propertiesConfig));
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    @Bean
    public DataSource dataSource(StorePropertiesConfig propertiesConfig) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(propertiesConfig.getDriverClassName());
        dataSource.setPassword(propertiesConfig.getPasssword());
        dataSource.setUsername(propertiesConfig.getUsername());
        dataSource.setUrl(propertiesConfig.getUrl());
        return dataSource;
    }
}
