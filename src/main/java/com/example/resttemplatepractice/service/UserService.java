package com.example.resttemplatepractice.service;

import com.example.resttemplatepractice.domain.UserInfo;
import com.example.resttemplatepractice.domain.UserRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    UserRequestDTO getAllUsers();
    public Map<String, List<UserInfo>> filterUserById(String id);
}
