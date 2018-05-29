package com.jcloud.express.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="userId")
public class User implements Serializable {

    @Id
    @Column(name = "user_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "first_name",nullable = false)
    @NotEmpty
    private String firstName;

    @Column(name = "last_name",nullable = false)
    @NotEmpty
    private String lastName;

    @Column(name = "nic",nullable = false)
    @NotEmpty
    @Length(min = 10,max = 11)
    private String nicNo;

    @Column(name = "email",nullable = false,unique = true)
    @Length(min = 7,max = 50)
    private String email;

    @Column(name = "user_name",nullable = false,unique = true)
    @Length(min = 4,max = 15)
    private String username;

    @Column(name = "password",nullable = false)
    @Length(min = 8)
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "expired")
    private boolean expired;

    @Column(name = "recovery_email")
    private String recoveryEmail;

    @Column(name = "recovery_phone")
    private String recoveryPhone;

    @Column(name = "logged_in")
    private boolean loggedIn;

    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

}
