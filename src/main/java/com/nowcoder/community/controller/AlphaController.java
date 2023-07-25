package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


@Controller
@RequestMapping("/alpha")//访问名，浏览器通过这个名字访问这个类
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody  //声明我们访问的是字符串不是网页
    public String sayHello() {
        return "Hello Spring Boot.";
    }

    @RequestMapping("/data") //要声明路径
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {  //response对象可以直接向浏览器输出任何值，不依赖于返回值
        // 获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath()); //请求路径
        Enumeration<String> enumeration = request.getHeaderNames();  //得到所有请求行的key，得到一个迭代器（很老），
        while(enumeration.hasMoreElements()) {  //对迭代器遍历
            String name = enumeration.nextElement();     //是否还有更多元素，如果有则取到请求行的一个名字，
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset = utf-8");
        try (
                PrintWriter writer = response.getWriter();
                ){
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GET请求，默认发送的请求是get请求，用于向服务器获取某些数据
    // /students?current=1&limit=20   想查询所有学生，查询路径，希望分页显示，当前是第几页current，每一页最多显示几条数据20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1")   int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10")   int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
    // 另一种方式
    //  /student/123   直接编排到路径中
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    // POST请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudents(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 响应HTML数据

    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    //不加注解@ResponseBody默认返回html
    public ModelAndView getTeacher() { //返回mode和view dispatchServer类调用controller里面一个类，这个类就是返回封装的两份数据
        ModelAndView mav = new ModelAndView();
        //模版里需要多少个变脸就add多少个数据即可
        mav.addObject("name", "张三");
        mav.addObject("age", 30);
        mav.setViewName("/demo/view");//放在template的一个demo的目录下，这个view指的是html
        return mav;
    }
    //开发尽量用这种方式，比较简洁
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) { //return string 返回的是view的路径
        // model是dispatchServer类看到有这个对象会自动实例化这个model对象，dispatcher持有这个对象的引用，往这个方法内部装数据他也能得到
        //上面的是model和view的数据都撞到一个对象里，model数据放到参数里，view的视图直接返回，返回的值给dispatcherServet类
        model.addAttribute("name", "北京大学");
        model.addAttribute("age", 80);
        return "/demo/view";
    }

    // 响应JSON数据（异步请求中）
    // Java对象 -> JASON字符串 -> JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        return emp;
    }
    //多个相似数据的结构的情况，返回多个员工
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age", 24);
        emp.put("salary", 9000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","王五");
        emp.put("age", 25);
        emp.put("salary", 10000.00);
        list.add(emp);

        return list;
    }

}
