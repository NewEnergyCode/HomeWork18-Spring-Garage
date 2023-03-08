package com.example.homework18.repository.dao;

import com.example.homework18.model.UserPass;
import com.example.homework18.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPassRepository extends JpaRepository<UserPass, Long>{

    UserPass findByUsername (String username);


}
