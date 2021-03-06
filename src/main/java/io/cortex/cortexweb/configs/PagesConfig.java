package io.cortex.cortexweb.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class PagesConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }

    @Bean(name = "cortexDataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/CortexAPI");
        dataSource.setUsername("CortexAPI");
        dataSource.setPassword("CortexAPI");

        return dataSource;
    }

//    @Bean
//    public AuthenticationManager securityContext() {
//        AuthenticationManager manager = new AuthenticationManagerImpl();
//        return manager;
//    }
}
