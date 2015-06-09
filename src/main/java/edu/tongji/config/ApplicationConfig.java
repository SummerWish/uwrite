package edu.tongji.config;

import edu.tongji.Application;
import edu.tongji.account.AccountRepository;
import edu.tongji.article.ArticleRepository;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import static org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(basePackageClasses = Application.class, excludeFilters = @Filter({Controller.class, Configuration.class}))
class ApplicationConfig {

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("/persistence.properties"));
        return ppc;
    }

    @Bean
    public static PegDownProcessor pegDownProcessor() {
        return new PegDownProcessor();
    }

    @Bean
    public static ArticleRepository articleRepository() {
        return new ArticleRepository();
    }

    @Bean
    public static AccountRepository accountRepository() {
        return new AccountRepository();
    }

}