package com.codegym.config;


import com.codegym.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.codegym")
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolve() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/products/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
