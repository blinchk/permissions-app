package com.laus.permissions.utils;

import com.laus.permissions.exception.ChainEndException;
import com.laus.permissions.exception.NotFoundException;
import com.laus.permissions.model.Permission;
import com.laus.permissions.repository.PermissionRepository;

import java.util.Optional;

public class PermissionValidator implements Validator<Permission> {
    private final PermissionRepository repository;

    public PermissionValidator(PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(Permission permission) {
        Permission parent = permission.getParent();
        if (parent == null) {
            return;
        }
        Long parentId = parent.getId();
        Optional<Permission> parentOptional = this.repository.findById(parentId);
        if (parentOptional.isPresent()) {
            if (parentOptional.get().isCannotBeAddedToChain()) {
                throw new ChainEndException();
            }
        } else {
            throw new NotFoundException();
        }
    }
}
