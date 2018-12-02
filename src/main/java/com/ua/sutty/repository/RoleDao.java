package com.ua.sutty.repository;


import com.ua.sutty.domain.Role;

public interface RoleDao {

    void create(Role role);

    void update(Role role);

    void remove(Role role);

    Role findByName(String name);

}
