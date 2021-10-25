package com.laus.permissions.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.laus.permissions.exception.ChainEndException;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "permission", uniqueConstraints = { @UniqueConstraint(columnNames = {"id"}) })
public class Permission {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    @JsonBackReference
    private Permission parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
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

    private boolean isLastInTheChain() {
        if (getParent() == null) return false;
        else if (getParent().getParent() == null) return false;
        else return getParent().getParent().getParent() != null;
    }

    public List<Permission> getChildren() {
        return children;
    }
}
