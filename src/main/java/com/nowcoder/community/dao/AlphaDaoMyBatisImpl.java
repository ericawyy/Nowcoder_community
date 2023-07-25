package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary //用同一个接口的时候加此注解有更高的优先级
//那么有一天我想将之前那个bean替换掉的时候，我只需要在新的bean加一个primary注解

public class AlphaDaoMyBatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "MyBatis";
    }
}
