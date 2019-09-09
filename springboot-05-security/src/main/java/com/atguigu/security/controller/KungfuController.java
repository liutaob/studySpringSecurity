package com.atguigu.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KungfuController {
    private final String PREFIX = "pages/";

    /**
     * 欢迎页
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "welcome";
    }

    /**
     * 登陆页
     *
     * @return
     */
    @GetMapping("/userlogin")
    public String loginPage() {
        return PREFIX + "login";
    }


    /**
     * level1页面映射
     *
     * @param path
     * @return
     */
    @GetMapping("/level1/{path}")
    public String level1(@PathVariable("path") String path) {
        return PREFIX + "level1/" + path;
    }

    /**
     * level2页面映射
     *
     * @param path
     * @return
     */
    @GetMapping("/level2/{path}")
    public String level2(@PathVariable("path") String path) {
        return PREFIX + "level2/" + path;
    }

    /**
     * level3页面映射
     *
     * @param path
     * @return
     */
    @GetMapping("/level3/{path}")
    public String level3(@PathVariable("path") String path) {
        return PREFIX + "level3/" + path;
    }

    /**
     * level4页面映射
     *
     * @param path
     * @return
     */
    @PreAuthorize("hasRole('VIP3') AND hasRole('VIP2') AND hasRole('VIP1')")
    @GetMapping("/level4/{path}")
    public String level4(@PathVariable("path") String path) {
        return PREFIX + "level4/" + path;
    }


    @GetMapping("/helloUser")
    @Secured({"ROLE_VIP1","ROLE_VIP2"})//只能满足或 固定前缀ROLE_
    @ResponseBody
    public String helloUser() {
        return "hello,user";
    }

    @GetMapping("/helloUser2")
    @PostAuthorize(" returnObject!=null &&  returnObject.username == authentication.name")
    @ResponseBody
    public User helloUser2() {
        Object pricipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user;
        if("anonymousUser".equals(pricipal)) {
            user = null;
        }else {
            user = (User) pricipal;
        }
        return user;
    }


}
