package com.miniprojecttwo.accountservice.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.RequestHandlerProvider;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.WebMvcRequestHandler;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.spring.web.readers.operation.HandlerMethodResolver;

import javax.servlet.ServletContext;
import java.util.*;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static springfox.documentation.RequestHandler.byPatternsCondition;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
public class SwaggerConfiguration {

  @Value("${spring.application.name}")
  private String applicationName;

  private ApiInfo apiInfo() {
    return new ApiInfo(applicationName + " REST API",
      "Some custom description of API.",
      "1.0",
      "Terms of service",
      new Contact("Amit Ghosh", "www.amitghosh.me", "amitbd1508@gmail.com"),
      "License of API",
      "API license URL",
      Collections.emptyList());
  }

  @Bean
  public Docket api() {
    return new Docket(SWAGGER_2)
      .securitySchemes(singletonList(new ApiKey("JWT", AUTHORIZATION, "header")))
      .securityContexts(singletonList(
        SecurityContext.builder()
          .securityReferences(
            singletonList(SecurityReference.builder()
              .reference("JWT")
              .scopes(new AuthorizationScope[0])
              .build()
            )
          )
          .build())
      )
      .apiInfo(apiInfo())
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.miniprojecttwo.accountservice.controller"))
      .build();
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope
      = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
  }

  // Hack to run swagger
  @Bean
  public InitializingBean removeSpringfoxHandlerProvider(DocumentationPluginsBootstrapper bootstrapper) {
    return () -> bootstrapper.getHandlerProviders().removeIf(WebMvcRequestHandlerProvider.class::isInstance);
  }

  @Bean
  public RequestHandlerProvider customRequestHandlerProvider(Optional<ServletContext> servletContext, HandlerMethodResolver methodResolver, List<RequestMappingInfoHandlerMapping> handlerMappings) {
    String contextPath = servletContext.map(ServletContext::getContextPath).orElse(Paths.ROOT);
    return () -> handlerMappings.stream()
      .filter(mapping -> !mapping.getClass().getSimpleName().equals("IntegrationRequestMappingHandlerMapping"))
      .map(mapping -> mapping.getHandlerMethods().entrySet())
      .flatMap(Set::stream)
      .map(entry -> new WebMvcRequestHandler(contextPath, methodResolver, tweakInfo(entry.getKey()), entry.getValue()))
      .sorted(byPatternsCondition())
      .collect(toList());
  }

  RequestMappingInfo tweakInfo(RequestMappingInfo info) {
    if (info.getPathPatternsCondition() == null) return info;
    String[] patterns = info.getPathPatternsCondition().getPatternValues().toArray(String[]::new);
    return info.mutate().options(new RequestMappingInfo.BuilderConfiguration()).paths(patterns).build();
  }
}
