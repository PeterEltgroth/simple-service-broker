package io.pivotal.cf.service.connector;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {

    public enum Role {Broker, User};

    private String name;
    private String password;
    private Role role;

    public User(@NonNull String name, @NonNull Role role) {
        this();
        this.name = name;
        this.role = role;
    }

    public User(@NonNull String name, @NonNull String password, @NonNull Role role) {
        this(name, role);
        this.password = password;
    }

    public User() {
        super();
    }
}