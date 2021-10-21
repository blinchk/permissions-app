package com.laus.permissions.service;

import com.laus.permissions.model.Permission;
import com.laus.permissions.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Optional<Permission> findById(long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }
}
