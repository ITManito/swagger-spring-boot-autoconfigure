package com.github.angelrmm.swaggerspringbootautoconfigure;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;


public class SwaggerCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//        获得标题，描述，swagger扫描基础包
        String title = context.getEnvironment().getProperty("swagger.title");
        String description = context.getEnvironment().getProperty("swagger.description");
        String basePackage = context.getEnvironment().getProperty("swagger.base-package");

//        判空 lack:缺乏
        if (StringUtils.isEmpty(title)) {
            throw new RuntimeException("Lack of swagger configuration:swagger.title");
        } else if (StringUtils.isEmpty(description)) {
            throw new RuntimeException("Lack of swagger configuration:swagger.title");
        } else if (StringUtils.isEmpty(basePackage)) {
            throw new RuntimeException("Lack of swagger configuration:swagger.title");
        } else {
            return true;
        }
    }
}