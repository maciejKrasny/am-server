package com.gameserver.gameserver.service;

import com.gameserver.gameserver.model.User;
import com.gameserver.gameserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(int idUser) {

        return userRepository.findById(idUser);
    }

    public String saveUser(User user) {

        if (user.getName().isEmpty() || user.getPassword().isEmpty()){
            return "Name or password can't be empty";
        } else {
            Optional<User> dbUser = userRepository.findByName(user.getName());
            if(dbUser.isPresent()) {
               return "This name is used";
            } else {
                userRepository.save(user);
            }
        }
        return "Successfull sign up";
    }

    public void deleteUser(int userId) {

        userRepository.deleteById(userId);
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        userRepository.findAll()
                .forEach(users::add);
        return users;
    }


}
