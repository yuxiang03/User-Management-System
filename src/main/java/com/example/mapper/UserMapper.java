package com.example.mapper;

import com.example.domain.Role;
import com.example.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();

    Long save(User user);

    void saveUserRoleRel(Long id, Long[] roleIds);

    void delUserRoleRel(Long userId);

    void del(Long userId);

    User findByUsernameAndPassword(String username, String password);
}
