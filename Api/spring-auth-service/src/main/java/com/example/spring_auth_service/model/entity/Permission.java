package com.example.spring_auth_service.model.entity;

import com.example.spring_auth_service.model.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseEntity {
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;
}
