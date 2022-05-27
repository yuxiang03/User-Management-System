package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.domain.Role;
import com.example.domain.User;
import com.example.mapper.RoleMapper;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> list() {
        List<User> userList = userMapper.findAll();
        //封装userList中的每一个User的roles的数据
        for (User user : userList) {
            //获得user的id
            Long id = user.getId();
            //将id作为参数，查询当前userId对应的Role集合数据
            List<Role> roles = roleMapper.findRoleByUserId(id);
            user.setRoles(roles);
        }
        return userList;
    }

    @Override
    public void save(User user, Long[] roleIds) {
        //第一步 向sys_user表存储数据
        Long userId = userDao.save(user);
        //第二步 向sys_user)role 关系表中存储多条数据
        userDao.saveUserRoleRel(userId,roleIds);
    }

    @Override
    public void del(Long userId) {
        //1.删除sys_user_role关系表
        userMapper.delUserRoleRel(userId);
        //2.删除sys_user表
        userMapper.del(userId);
    }

    @Override
    public User login(String username, String password) {
        try {
            return userDao.findByUsernameAndPassword(username,password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
}
