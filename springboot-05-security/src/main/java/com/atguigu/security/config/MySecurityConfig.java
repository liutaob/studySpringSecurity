package com.atguigu.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/level1/**").hasRole("VIP1").//或注解方式方法拦截
                antMatchers("/level2/**").hasRole("VIP2").
                antMatchers("/level3/**").hasRole("VIP3")
                .antMatchers("/userlogin").permitAll();
        //任何请求都要认证2019/9/9 开启这个就要放行某些自定义业务路径 包括登录  否则只对以上配置的路径作拦截 登录等请求交由底层处理
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.csrf().disable();//关闭跨站拦截
        //开启自动配置的登录功能，未开启403，开启来到登录页面
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin"); // 自定义登录页面    需要修改前端请求路径
        //1，/login来到登陆页 自动生成的
        //2，/login?error 重定向 表示登录失败
        //3，更多详细规定
        //4，默认post形式/login处理登录 get请求是来到登录页面
        //5,一旦定制loginPage页面，该请求post请求就是处理登录

        //开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");//自定义注销成功返回主页
        //1，访问/logout 并清空session
        //2，注销成功 默认返回/logout?logout

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember");
        //登录成功 cookie
        //注销 删除cookie
    }

    //定义认证规则
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService()
        //模拟从数据库中拿到数据认证
        auth.inMemoryAuthentication().withUser("zhangsan").password("123456").roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password("123456").roles("VIP1", "VIP3").and().withUser("liut").password("123456")
                .roles("VIP1","VIP2","VIP3").and().withUser("lpc").password("123456").roles("VIP3");
    }
}
