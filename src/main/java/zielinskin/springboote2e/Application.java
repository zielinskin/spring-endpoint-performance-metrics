package zielinskin.springboote2e;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@SpringBootApplication
@EntityScan(basePackages={"zielinskin.springboote2e.data"})
@EnableJpaRepositories(basePackages = {"zielinskin.springboote2e.data"})
public class Application implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api", "/swagger-ui.html");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public GroupedOpenApi testDocket() {
        return GroupedOpenApi.builder()
                .group("Endpoint Testing")
                .pathsToMatch("/endpoints/**")
                .build();
    }

    @Bean
    public GroupedOpenApi testConfigurationsDocket() {
        return GroupedOpenApi.builder()
                .group("Endpoint Test Configurations")
                .pathsToMatch("/endpoint-configurations/**")
                .build();
    }
}
