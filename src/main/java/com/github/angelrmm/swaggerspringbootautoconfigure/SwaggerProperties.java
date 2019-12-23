package com.github.angelrmm.swaggerspringbootautoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


//预配置属性
@ConfigurationProperties(prefix = "swagger")
@Data
@Component
public class SwaggerProperties {

    /**
     * 标题，必填
     */
    private String title;

    /**
     * 维护人
     */
    private Contact contact = new Contact();
    /**
     * 版本
     */
    private String version;
    /**
     * 描述，必填
     */
    private String description;
    /**
     * swagger扫描的基础包，必填
     */
    private String basePackage = "";
    /**
     * 需要处理的基础URL规则，默认：/**
     */
    private String basePath = "/**";
    /**
     * 需要排除的URL规则，默认：空
     */
    private String excludePath = "";
    /**
     * 许可证
     */
    private String license;
    private String licenseUrl;

    //    维护人信息 内部类 todo
    @Data
    public static class Contact {
        /**
         * 维护人名
         */
        private String name;
        /**
         * 维护人email
         */
        private String email;
        /**
         * 维护人url
         */
        private String url;
    }
}