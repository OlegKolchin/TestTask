package org.kolchin.TestTask;

import lombok.AllArgsConstructor;
import org.kolchin.TestTask.dao.RoleRepository;
import org.kolchin.TestTask.dao.UserRepository;
import org.kolchin.TestTask.model.Role;
import org.kolchin.TestTask.model.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;


@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public void run(ApplicationArguments args) {

        createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("OPERATOR");
        createRoleIfNotFound("ANALYST");

        Role roleAdmin = roleRepository.findByRoleName("ADMIN");
        Role roleOperator = roleRepository.findByRoleName("OPERATOR");
        Role roleAnalyst = roleRepository.findByRoleName("ANALYST");

        userRepository.save(new User("admin", "Ivan", "Aa123456", Arrays.asList(roleAdmin, roleOperator, roleAnalyst)));
        userRepository.save(new User("op_admin", "Petr", "Aa123456", Arrays.asList(roleAdmin, roleOperator)));
        userRepository.save(new User("analyst", "Ilya", "Aa123456", Arrays.asList(roleAnalyst)));
        userRepository.save(new User("analyst1", "Oleg", "Aa123456", Arrays.asList(roleAnalyst)));
    }

    Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByRoleName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}
