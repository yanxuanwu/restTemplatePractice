package com.example.resttemplatepractice.controller;

import com.example.resttemplatepractice.domain.UserInfo;
import com.example.resttemplatepractice.domain.UserRequestDTO;
import com.example.resttemplatepractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserRequestDTO> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Map<String, List<UserInfo>>> getUsersIdLargerThan(String id){
        return new ResponseEntity<>(userService.filterUserById(id), HttpStatus.OK);
    }
}

/**
 * 1.exception
 * 2. log
 * 3. documentation(swagger:auto gen doc)
 * 4. rest template timeout
 * 3. 3rd party api no response
 *      a.retry(time = times)
 *      b.cache(database) + scheduler(update)
 *          user -read -> cache/db -if cannot find data -> 3rd party api
 *      c.circuit breaker
 *          3 out of 5
 *          user -> circuit breaker(on) -> return default message
 *                          /backend thread
 *                          --> try to visit 3rd party api -> cannot connect -> do nothing
 *                                  /
 *                                  --> can visit -> turn it off
 *
 */