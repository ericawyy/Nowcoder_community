package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class) //为了和正式代码一样有那些配置类,加上这个注解
//怎样得到ioc给我们创建的容器呢？哪个类想得到就实现以下接口
//spring容器在扫描的时候扫描到以下接口和方法就会在set里把自身传进来
//所以我们用一个成员变量记录一下这个spring容器applicationcontext后面就可以直接使用他了
class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	//传入参数
		this.applicationContext = applicationContext;
	}

	@Test //测试方法，在测试方法里面使用这个spring容器
	public void testApplicationContext() {
		System.out.println(applicationContext);

		//要在容器里自动获取他装配的bean 获取bean的方法：getBean
		//面向的是接口，如果实现接口的类变了这里是不用动的
		//降低了bean之间(hibernate-mybatis)的耦合度，调用方和实现类不会有直接的关系
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select()); //调用查询方法，并将结果输出 hibernate

		alphaDao = applicationContext.getBean( "alphaHibernate", AlphaDao.class); //指定了名字
		System.out.println(alphaDao.select());
	}

	@Test //测试以下bean管理
	public void testBeanManagement() {
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
		//spring每次都是单实例的
		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat =
				applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format((new Date())));
	}


	@Autowired
	@Qualifier("alphaHibernate") //spring就会把名为"alphaHibernate"的bean注入给他
	private AlphaDao alphaDao; //希望注入不是默认优先级batis，需要加一个qualifier

	@Autowired
	private AlphaService alphaService;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI(){
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}




}
//建一个包，专门存放访问数据库的bean

//bean默认的名字是类名，首字母小写，如果我们一部分想用这个bean，另一部分想用另一个，可以自定义bean名