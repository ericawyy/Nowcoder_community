package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration //表明这是一个配置类，不是一个普通的类
public class AlphaConfig {
    @Bean
    public SimpleDateFormat simpleDateFormat(){ //把java自带的这个方法SimpleDateFormat，方法名就是bean的名字
        return new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
    }//这个方法返回的对象将被装配到容器里
}
