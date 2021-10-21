package com.laus.permissions.service;

import com.laus.permissions.model.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {
    Optional<Permission> findById(long id);
    List<Permission> findAll();
}
