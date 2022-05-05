package com.example.service.impl;

import com.example.dao.RoleDao;
import com.example.domain.Role;
import com.example.mapper.RoleMapper;
import com.example.service.RoleService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);

    private RoleDao roleDao;

    public RoleServiceImpl() throws IOException {
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> list(){
       /* List<Role> roleList = roleDao.findAll();
        return roleList;*/
        List<Role> roleList = mapper.findAll();
        return  roleList;

    }

    @Override
    public void save(Role role) {
        //roleDao.save(role);
        mapper.save(role);
    }

    @Override
    public void del(Long roleId) {
        //1.删除sys_user_role关系表
        mapper.delRoleRel(roleId);
        //roleDao.delRoleRel(roleId);
        //2。删除sys_user表
        mapper.del(roleId);
    }
}
