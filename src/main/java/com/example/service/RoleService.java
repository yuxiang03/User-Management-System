package com.example.service;

import com.example.domain.Role;

import java.io.IOException;
import java.util.List;

public interface RoleService {
    public List<Role> list() throws IOException;

    void save(Role role);

    void del(Long userId);
}
