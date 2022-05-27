package com.example.mapper;

import com.example.domain.Role;

import java.util.List;

public interface RoleMapper {
    List<Role> findAll();

    void save(Role role);

    List<Role> findRoleByUserId(Long id);

    void del(Long userId);

    void delRoleRel(Long roleId);
}
