package com.nowcoder.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class LoggerTests {

    private static final Logger logger = LoggerFactory.getLogger(LoggerTests.class);  //实例化记录日志组件的接口

    @Test
    public void testLogager() {
        System.out.println(logger.getName());

        logger.debug("debug log");  //一般用的最低级别就是debug
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");
    }

}
