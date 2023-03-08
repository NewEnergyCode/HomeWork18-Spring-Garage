package com.example.homework18.repository.dao;


import com.example.homework18.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
Role findByName (String username);

}
