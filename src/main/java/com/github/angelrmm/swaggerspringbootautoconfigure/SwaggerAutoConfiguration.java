package com.github.angelrmm.swaggerspringbootautoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


//装配配置属性
@Configuration
//自动装配属性类，读取yaml文件内容
@EnableConfigurationProperties(SwaggerProperties.class)
//检验类
@Conditional(SwaggerCondition.class)
//配置文件中swagger值为true时，实例化此类，可以不填？
@ConditionalOnProperty(prefix = "swagger", value = "true", matchIfMissing = true)
public class SwaggerAutoConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
//    Docket创建restApi
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage())).paths(PathSelectors.any()).build();
    }

    //    构建api文档信息(标题，维护人信息，版本，描述)
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(), swaggerProperties.getContact().getEmail()))
                .version(swaggerProperties.getVersion())
                .description(swaggerProperties.getDescription()).build();

    }

    //    添加资源处理器
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(swaggerProperties.getBasePath()).addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);

    }

    //    解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH").allowCredentials(true).maxAge(3600);
    }
}