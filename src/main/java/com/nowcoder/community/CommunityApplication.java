package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommunityApplication {

	public static void main(String[] args) {

		SpringApplication.run(CommunityApplication.class, args);
	}

}
//底层不仅启动了tomcat，还自动帮我们创建了spring容器
//spring容器创建之后，会自动扫描某些包下的某些bin，将这些bin装配到容器里
//springapplication启动的时候是需要被配置的，communityapplication就是一个配置文件
//他会扫描配置类所在的包以及子包下面的bin，并且bin下面要有@controller（处理请求）这样的注解就会被扫描，如果没有就不会被扫描
//加service（业务组建），component（任何业务），repository（处理数据库）注解也是可以被扫描的，都是由componnent来实现的，只是语义区别