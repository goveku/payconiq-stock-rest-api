package nl.payconiq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *  Swagger config
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "nl.payconiq.stock.controllers.rest")
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("payconiq")
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("nl.payconiq.stock.controllers.rest"))
                .build();
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "Well come to Payconiq Stocks Service",
                "you can find/update/create stock details using Payconiq Stocks REST API service.",
                "1.0",
                "Terms of service",
                new Contact("Venu", "", "venu.kumar.224@gmail.com"),
                "Payconiq Stock Services Rest API License 1.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
    }
}