package com.zt.operate.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class ServerConfig {


    /**
     * 产品首页访问路径
     */
    @Value("${product.url}")
    String productUrl;
    /**
     * 产品名称
     */
    @Value("${product.name}")
    String productName;
    /**
     * 产品地址
     */
    @Value("${webroot.path}")
    String webRoot;

}
