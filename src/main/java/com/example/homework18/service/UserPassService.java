package com.example.homework18.service;

import com.example.homework18.model.UserPass;
import com.example.homework18.model.UserPassDto;

public interface UserPassService {


     void creatUser(UserPassDto userPassDto);

     UserPass findByUsername(String username);
}
