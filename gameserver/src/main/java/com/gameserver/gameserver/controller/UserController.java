package com.gameserver.gameserver.controller;

import com.gameserver.gameserver.model.User;
import com.gameserver.gameserver.repository.UserRepository;
import com.gameserver.gameserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping(value = "/users/all", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users;
    }

    @RequestMapping( value = "/users",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String addUser(@RequestParam("name") String name, @RequestParam("password") String password) {

        String addUserResponse = userService.saveUser(new User(name, password));
        return addUserResponse;
    }
}
