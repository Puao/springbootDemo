package com.puao.controller;

import com.puao.bean.CurrentInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/login")
public class loginController {

    @RequestMapping("info")
    @ResponseBody
    public String info(HttpServletRequest request , HttpServletResponse response){
        HttpSession session = request.getSession();
        String str = session.getAttribute("user").toString();
        if(Objects.isNull(str)){
            return "there is no session";
        }
        return str;
    }

    @RequestMapping("/loginIn")
    @ResponseBody
    public String userloginIn(HttpServletRequest request , HttpServletResponse response){
        HttpSession session = request.getSession();
        CurrentInfo info = new CurrentInfo();

        info.setIp(request.getLocalAddr());
        info.setPort(String.valueOf(request.getLocalPort()));
        info.setUser(request.getLocalName());
        session.setAttribute("user",info.toString());
        return "success";
    }

    @RequestMapping("/loginOut")
    @ResponseBody
    public String userLoingOut (HttpServletRequest request , HttpServletResponse response){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        if(Objects.isNull(session.getAttribute("user"))){
            return "susccess";
        }
        return "failure";
    }
}
