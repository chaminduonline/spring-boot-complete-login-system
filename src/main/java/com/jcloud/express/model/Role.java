package com.jcloud.express.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="roleId")
@Data
public class Role implements Serializable{

    public Role(){}

    public Role(String role) {
        this.role = role;
    }

    @Id
    @Column(name = "role_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(name = "role",nullable = false,unique = true)
    private String role;

    @Column(name = "description")
    private String description;

    @Column(name = "enabled")
    private boolean enabled = true;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }

}
