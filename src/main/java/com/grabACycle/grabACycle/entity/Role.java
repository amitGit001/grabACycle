package com.grabACycle.grabACycle.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(name = "userRole", nullable = false, columnDefinition = "VARCHAR(255)")
    private String userRole;

    public Role(String userRole) {
        this.userRole = userRole;
    }
}
