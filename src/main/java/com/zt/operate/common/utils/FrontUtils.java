package com.zt.operate.common.utils;

import com.zt.operate.common.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 前台工具类
 *
 * @author liufang
 */
@Component
public class FrontUtils {
    /**
     * 页面完整地址
     */
    public static final String LOCATION = "location";
    public static final String PRODUCT_INDEX_URL = "productUrl";
    public static final String PRODUCT_NAME = "productName";
    private static FrontUtils frontUtils;
    @Autowired
    public ServerConfig serverConfig;

    /**
     * 为前台模板设置公用数据
     *
     * @param request
     * @param map
     */
    public static void frontData(HttpServletRequest request,
                                 Map<String, Object> map) {
        String location = RequestUtils.getLocation(request);
        String productUrl = frontUtils.serverConfig.getProductUrl();
        String productName = frontUtils.serverConfig.getProductName();

        map.put(LOCATION, location);
        map.put(PRODUCT_NAME, productName);
        map.put(PRODUCT_INDEX_URL, productUrl);
    }

    @PostConstruct
    public void init() {
        frontUtils = this;
        frontUtils.serverConfig = this.serverConfig;
    }

    public static String getWebRoot() {
        return frontUtils.serverConfig.getWebRoot();
    }

}
