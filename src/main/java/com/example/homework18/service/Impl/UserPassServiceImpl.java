package com.example.homework18.service.Impl;

import com.example.homework18.model.Role;
import com.example.homework18.model.UserPass;
import com.example.homework18.model.UserPassDto;
import com.example.homework18.repository.dao.RoleRepository;
import com.example.homework18.repository.dao.UserPassRepository;
import com.example.homework18.service.UserPassService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserPassServiceImpl implements UserPassService {
    private UserPassRepository userPassRepository;
    private final PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    public UserPassServiceImpl(UserPassRepository userPassRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userPassRepository = userPassRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void creatUser(UserPassDto userPassDto) {
        System.out.println("method open");
        UserPass userPass = new UserPass();
        userPass.setUsername(userPassDto.getUsername());
        userPass.setPassword(passwordEncoder.encode(userPassDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        userPass.setRole(Arrays.asList(role));
        userPassRepository.save(userPass);

    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public UserPass findByUsername(String username) {
        return userPassRepository.findByUsername(username);
    }

}
