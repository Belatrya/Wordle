package com.belatry.config;

import com.belatry.model.Dictionary;
import com.belatry.model.DictionaryFileStorage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Represents spring configuration.
 */
@Configuration
@ComponentScan({"com.belatry"})
@PropertySource("classpath:application.properties")
@EnableWebMvc
@AllArgsConstructor
@Import({
        org.springdoc.webmvc.ui.SwaggerConfig.class,
        org.springdoc.core.SwaggerUiConfigProperties.class, org.springdoc.core.SwaggerUiOAuthProperties.class,
        org.springdoc.webmvc.core.SpringDocWebMvcConfiguration.class,
        org.springdoc.webmvc.core.MultipleOpenApiSupportConfiguration.class,
        org.springdoc.core.SpringDocConfiguration.class, org.springdoc.core.SpringDocConfigProperties.class,
        org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.class
})
public class ConfigurationContext implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    @Bean
    @Value("${all.words.path}")
    Dictionary allWordsDictionary(String path) {
        return new DictionaryFileStorage(path);
    }

    @Bean
    @Value("${hidden.words.path}")
    Dictionary hiddenWordsDictionary(String path) {
        return new DictionaryFileStorage(path);
    }
}
