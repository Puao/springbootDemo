package com.puao.controller;


import com.puao.service.IBallService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@MapperScan("com.puao.dao")
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = {"com.puao.*"})
public class IndexController extends SpringBootServletInitializer {


    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IndexController.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IndexController.class, args);
    }

}