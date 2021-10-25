package com.laus.permissions.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.laus.permissions.exception.ChainEndException;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "permission", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Permission {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty.")
    @Length(min = 3, max = 256, message = "Name length should be between 3 and 256 characters.")
    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = { CascadeType.MERGE })
    private Permission parent;

    @OneToMany(mappedBy = "parent")
    private List<Permission> children;

    public Long getId() {
        return id;
    }

    public Permission getParent() {
        return parent;
    }

    public void setParent(Permission parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public boolean isNotLastInTheChain() {
        if (getParent() == null) return true;
        else if (getParent().getParent() == null) return true;
        else return getParent().getParent().getParent() != null;
    }
}
