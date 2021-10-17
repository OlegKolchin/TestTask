package org.kolchin.TestTask.controller;

import lombok.AllArgsConstructor;
import org.kolchin.TestTask.dao.UserRepository;
import org.kolchin.TestTask.model.User;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class UserController {

    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new User(user.getLogin(), user.getName(), user.getPassword()))
                .collect(Collectors.toList());
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByName(String login) {
        return userRepository.findById(login).get();
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void deleteById(String login) {
        userRepository.deleteById(login);
    }


}
