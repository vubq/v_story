package com.v_story.v_story_be.Entities;

import com.v_story.v_story_be.Enums.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "USER_NAME", nullable = false, length = 255)
    private String userName;

    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;

    @Column(name = "ROLES")
    private Enum<UserEnum> roles;

    @Column(name = "STATUS", nullable = false, length = 100)
    private String status;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "UPDATE_AT")
    private Timestamp updateAt;
}
