package com.example.service.impl;

import com.example.dao.RoleDao;
import com.example.domain.Role;
import com.example.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> list() {
        List<Role> roleList = roleDao.findAll();
        return roleList;
    }

    @Override
    public void save(Role role) {

        roleDao.save(role);
    }

    @Override
    public void del(Long roleId) {
        //1.删除sys_user_role关系表
        roleDao.delRoleRel(roleId);
        //2。删除sys_user表
        roleDao.del(roleId);
    }
}
