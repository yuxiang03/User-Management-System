package com.example.controller;

import com.example.domain.Role;
import com.example.domain.User;
import org.mindrot.jbcrypt.BCrypt;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    private static final String SALT = "$2a$10$Nsf/5GOl9JZtbpWHjykN/4";

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session){
        String BCryptPassword = BCrypt.hashpw(password,SALT);
        //String md5Password = EnctyptToMD5.enctyptToMD5(password);
        User user = userService.login(username,BCryptPassword);
        if(user != null){
            //登陆成功 将user存储到session
            session.setAttribute("user",user);
            return "redirect:/index.jsp";
        }
        return "redirect:/login.jsp";
    }

    @RequestMapping("/del/{userId}")
    public String del(@PathVariable("userId") Long userId){
        userService.del(userId);
        return "redirect:/user/list";
    }

    @RequestMapping("/save")
    public String save(User user,Long[] roleIds){
        String password = BCrypt.hashpw(user.getPassword(),SALT);
        //String password = EnctyptToMD5.enctyptToMD5(user.getPassword());
        user.setPassword(password);
        userService.save(user,roleIds);
        return "redirect:/user/list";
    }

    @RequestMapping("/saveUI")
    public ModelAndView saveUI() throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }

    @RequestMapping("/list")
    public ModelAndView list(){
        List<User> userList = userService.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }
}
