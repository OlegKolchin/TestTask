package org.kolchin.TestTask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @NotNull
    @Pattern(regexp = "^(?=\\s*\\S).*$", message = "String must contains at least one non-whitespace character")
    private String login;

    @NotNull
    @Pattern(regexp = "^(?=\\s*\\S).*$", message = "String must contains at least one non-whitespace character")
    private String name;


    @NotNull
    @Pattern(regexp = "(?=.*\\d)(?=.*[A-Z]).*", message = "Password has to contain at least 1 uppercase and 1 number.")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }
}
