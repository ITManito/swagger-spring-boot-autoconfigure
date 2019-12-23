package com.github.angelrmm.swaggerspringbootautoconfigure;

import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
@EnableSwagger2
public @interface EnableAngelSwagger2 {
}