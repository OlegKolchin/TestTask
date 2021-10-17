package org.kolchin.TestTask.api;

import lombok.AllArgsConstructor;
import org.kolchin.TestTask.controller.UserController;
import org.kolchin.TestTask.model.ResponseMessage;
import org.kolchin.TestTask.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserApi {

    private UserController userController;

    @GetMapping("/")
    public List<User> findAll() {
        return this.userController.findAll();
    }

    @GetMapping("/{login}")
    public User findByName(@PathVariable String login) {
        return userController.findByName(login);
    }

    @PostMapping("/")
    public ResponseMessage save(@RequestBody User user) {
        try {
            this.userController.save(user);
            return new ResponseMessage(true);
        } catch (Exception e) {
            return new ResponseMessage(false, e.toString());
        }
    }

    @DeleteMapping
    public void deleteAll() {
        userController.deleteAll();
    }

    @DeleteMapping("/{login}")
    public ResponseMessage deleteByID(@PathVariable String login) {
        try {
            this.userController.deleteById(login);
            return new ResponseMessage(true);
        } catch (Exception e) {
            return new ResponseMessage(false, e.toString());
        }
    }

    @PutMapping("/{login}")
    public ResponseMessage update(@PathVariable String login, @RequestBody User user) {
        try {
            User rsl = userController.findByName(login);
            rsl.setLogin(user.getLogin());
            rsl.setPassword(user.getPassword());
            rsl.setName(user.getName());
            rsl.setRoles(user.getRoles());
            this.userController.deleteById(login);
            this.userController.save(rsl);
            return new ResponseMessage(true);
        } catch (Exception e) {
            return new ResponseMessage(false, e.toString());
        }
    }
}
