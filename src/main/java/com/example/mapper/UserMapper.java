package com.example.mapper;

import com.example.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();

    void saveUserRoleRel(Long id, Long[] roleIds);

    void delUserRoleRel(Long userId);

    void del(Long userId);

    User findByUsernameAndPassword(String username, String password);
}
