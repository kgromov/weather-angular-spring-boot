package org.kgromov.weather.archive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.CacheControl;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.resource.EncodedResourceResolver;
import org.springframework.web.reactive.resource.PathResourceResolver;

import java.time.Duration;
import java.util.List;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RequestPredicates.pathExtension;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class WebConfig implements WebFluxConfigurer {

    @Bean
    RouterFunction<ServerResponse> redirectToIndex() {
        ClassPathResource index = new ClassPathResource("static/index.html");
        List<String> extensions = List.of("js", "css", "ico", "png", "jpg", "gif");
        RequestPredicate spaPredicate = path("/api/**").or(path("/error"))
                .or(pathExtension(extensions::contains))
                .negate();

        return route().resource(spaPredicate, index).build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*.html")
                .addResourceLocations("classpath:/resources/static")
                .setCacheControl(CacheControl.maxAge(Duration.ofHours(1)));

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/resources/static/")
                .setCacheControl(CacheControl.maxAge(Duration.ofHours(1)))
                .resourceChain(true)
                .addResolver(new EncodedResourceResolver())
                .addResolver(new PathResourceResolver());
    }
}
