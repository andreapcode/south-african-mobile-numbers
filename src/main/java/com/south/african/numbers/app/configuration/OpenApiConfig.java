package com.south.african.numbers.app.configuration;


import com.south.african.numbers.app.utils.constant.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI config() {
        return new OpenAPI()
                .info(new Info().title(Constant.Config.TITLE)
                        .description(Constant.Config.DESCRIPTION)
                        .version(Constant.Config.V_1));
    }
}


