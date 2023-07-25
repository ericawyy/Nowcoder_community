package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype") //单实例不用写括号里的这些，如果想每次创建一个新的实例，就加上prototype；但一般都是单实例
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao; //处理查询业务就可以调用这个了

    public AlphaService() {
        System.out.println("实例化AlphaService");

    }

    @PostConstruct //注解代表这个方法会在构造器之后调用
    public void init() {
        System.out.println("初始化AlphaService");
    }

    @PreDestroy //在对象销毁之前调用它
    public void destroy() {
        System.out.println("销毁AlphaService");
    }

    public String find(){
        return alphaDao.select();
    }

}
