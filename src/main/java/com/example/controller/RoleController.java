package com.example.controller;

import com.example.domain.Role;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

//对敏感信息进行加密 （密码：bcrypt）加盐
// 邮箱，电话号码等使用对称加密

@RequestMapping("/role")
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    /*public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }*/

    @RequestMapping("/del/{roleId}")
    public String del(@PathVariable("roleId") Long roleId){
        roleService.del(roleId);
        return "redirect:/role/list";
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/list";
    }

    @RequestMapping("/list")
    public ModelAndView list() throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        //设置模型
        modelAndView.addObject("roleList",roleList);
        //设置视图
        modelAndView.setViewName("role-list");
        return modelAndView;
    }
}
