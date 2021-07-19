package com.yqz.yqzdemojdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }



    //后台监控功能
//    因为springboot内置了servlet容器，所有没有web.xml,可以用替代方法：注册一个 ServletRegistrationBean
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //后台需要有人登录
//        初始化参数
        HashMap<String, String> initParameters = new HashMap<>();
//        登录的key是固定的 loginUsername 和 loginPassword
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","123456");
//        允许谁可以访问
        initParameters.put("allow","");
//        禁止谁能访问
        bean.setInitParameters(initParameters);
        return bean;
    }


//    filter
    @Bean
    public FilterRegistrationBean webStartFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
//        可以过滤哪些请求
        HashMap<String, String> initParameters = new HashMap<>();
//        这些东西不进行统计
        initParameters.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        return bean;
    }
}
