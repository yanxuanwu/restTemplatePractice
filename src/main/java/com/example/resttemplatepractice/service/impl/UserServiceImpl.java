package com.example.resttemplatepractice.service.impl;

import com.example.resttemplatepractice.domain.UserInfo;
import com.example.resttemplatepractice.domain.UserRequestDTO;
import com.example.resttemplatepractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate;

    @Value("${user.rest.url}")
    private String url;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserRequestDTO getAllUsers() {
        UserRequestDTO userRequestDTO = restTemplate.getForObject(url, UserRequestDTO.class);
        return userRequestDTO;
    }

    @Override
    public Map<String, List<UserInfo>> filterUserById(String id) {
        UserRequestDTO userRequestDTO = restTemplate.getForObject(url, UserRequestDTO.class);
        List<UserInfo> userInfos = userRequestDTO.getUserInfos().stream()
                .filter(u -> Long.valueOf(u.getId()) >= Long.valueOf(id))
                .collect(Collectors.toList());
        Map<String, List<UserInfo>> res = new HashMap<>();
        res.put("content", userInfos);
        return res;
    }
}
