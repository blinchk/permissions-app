package com.laus.permissions.model;

import com.laus.permissions.exception.ChainEndException;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission {
    @GeneratedValue
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @OneToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private Permission parent;

    @OneToOne
    @JoinColumn(name = "children_id", nullable = true)
    private Permission children;

    public Permission() {

    }

    public Permission(Permission parent) {
        this.parent = parent;
    }

    public Permission getChildren() {
        return children;
    }

    public void setChildren(Permission children) {
        if (isLastInTheChain()) throw new ChainEndException();
        this.children = children;
    }

    public boolean isLastInTheChain() {
        if (parent == null) return false;
        else return parent.parent != null;
    }
}
