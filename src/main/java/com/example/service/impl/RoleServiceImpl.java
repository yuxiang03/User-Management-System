package com.example.service.impl;

import com.example.domain.Role;
import com.example.mapper.RoleMapper;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> list(){
        return roleMapper.findAll();
    }

    @Override
    public void save(Role role) {
        roleMapper.save(role);
    }

    @Override
    public void del(Long roleId) {
        //1.删除sys_user_role关系表
        roleMapper.delRoleRel(roleId);
        //2。删除sys_user表
        roleMapper.del(roleId);
    }
}
