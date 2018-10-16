package atc.apidoc.docs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;


/**
 * Configuration for Springfox generated API documentation.
 */
@Configuration
@EnableSwagger2
@ComponentScan( "atc.apidoc" )
public class SpringfoxConfig
{
    private static final Logger logger = LoggerFactory.getLogger( SpringfoxConfig.class );


    /**
     * Configure the springfox Docket.
     *
     * @return a Docket object
     */
    @Bean
    public Docket buildDocket()
    {
        final String apiVersion = "1.0.0";

        final Docket docket = new Docket( DocumentationType.SWAGGER_2 )
              .groupName( apiVersion )
              .securitySchemes( Collections.singletonList( apiKeySecurityScheme() ) )
              .protocols( newHashSet( "https" ) )
              .select()
              .apis( RequestHandlerSelectors.basePackage( "atc.apidoc" ) )
              .paths( PathSelectors.regex( "/.*" ) )
              .build()
              .produces( Set.of( "application/json" ) )
              .genericModelSubstitutes( ResponseEntity.class )
              .useDefaultResponseMessages( false )
              .directModelSubstitute( LocalDate.class, java.sql.Date.class )
              .directModelSubstitute( LocalDateTime.class, java.util.Date.class )
              .directModelSubstitute( LocalTime.class, java.lang.String.class );

        docket.ignoredParameterTypes( Authentication.class, ModelMap.class );

        docket.apiInfo( apiInfo( ( apiVersion ) ) );

        return docket;
    }

    private SecurityScheme apiKeySecurityScheme()
    {
        return new BasicAuth( "ApiKey" );
    }


    private ApiInfo apiInfo( final String version )
    {
        return new ApiInfoBuilder()
              .title( "Example REST API" )
              .version( version )
              .build();
    }
}
