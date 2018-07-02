package com.canoerent.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    Set<User> userSet;

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Role() {

    }

    public Role(String name) {
        this.name = name;


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
