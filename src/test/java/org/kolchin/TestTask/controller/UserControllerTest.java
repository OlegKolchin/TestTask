package org.kolchin.TestTask.controller;

import org.junit.jupiter.api.Test;
import org.kolchin.TestTask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.NoSuchElementException;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void whenAddAndFind() {
        User user =  new User("admin3", "Petr", "12Aasdasda");
        userController.save(user);
        assertThat(userController.findByName("admin3").getLogin(), is(user.getLogin()));
    }

    @Test
    public void whenUpdateAndFind() {
        User user =  new User("admin3", "Petr", "12Aasdasda");
        userController.save(user);
        User user2 = new User("admin3", "Vasiliy", "12Aasdasda");
        userController.save(user2);
        assertThat(userController.findByName("admin3").getName(), is(user2.getName()));
    }

    @Test
    public void whenDelete() {
        User user =  new User("admin3", "Petr", "12Aasdasda");
        userController.save(user);
        userController.deleteById("admin3");
        assertThrows(NoSuchElementException.class,
                () -> userController.findByName("admin3"));
    }

    @Test
    public void whenDeleteAll() {
        User user =  new User("admin3", "Petr", "12Aasdasda");
        userController.save(user);
        User user2 = new User("admin4", "Vasiliy", "12Aasdasda");
        userController.save(user2);
        userController.deleteAll();
        assertThrows(NoSuchElementException.class,
                () -> userController.findByName("admin4"));
    }

    @Test
    public void whenDeleteAllSecondTest() {
        User user =  new User("admin3", "Petr", "12Aasdasda");
        userController.save(user);
        User user2 = new User("admin4", "Vasiliy", "12Aasdasda");
        userController.save(user2);
        userController.deleteAll();
        assertThrows(NoSuchElementException.class,
                () -> userController.findByName("admin3"));
    }
}