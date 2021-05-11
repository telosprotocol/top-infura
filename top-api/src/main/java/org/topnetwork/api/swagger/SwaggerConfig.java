package org.topnetwork.api.swagger;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author CasonCai
 * @since 2021/5/6 10:23 上午
 **/
@Configuration
@EnableWebMvc
@EnableOpenApi
public class SwaggerConfig {
}
