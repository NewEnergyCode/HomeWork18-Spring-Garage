package com.example.homework18.service;

import com.example.homework18.model.Role;
import com.example.homework18.model.UserPass;
import com.example.homework18.repository.dao.UserPassRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserPassRepository userPassRepository;

    public CustomUserDetailService(UserPassRepository userPassRepository) {
        this.userPassRepository = userPassRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPass userPass = userPassRepository.findByUsername(username);
        if (userPass != null) {
            return new org.springframework.security.core.userdetails.User(userPass.getUsername(),
                    userPass.getPassword(),
                    mapRolesToAuthorities(userPass.getRole()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}
