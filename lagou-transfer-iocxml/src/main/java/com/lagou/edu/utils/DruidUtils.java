package com.lagou.edu.utils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author 应癫
 */
public class DruidUtils {

    private DruidUtils(){
    }

    private static DruidDataSource druidDataSource = new DruidDataSource();


    static {
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8&autoReconnect=true");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("111111");

    }

    public static DruidDataSource getInstance() {
        return druidDataSource;
    }

}
