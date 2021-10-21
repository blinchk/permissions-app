package com.laus.permissions.repository;

import com.laus.permissions.model.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
    public Optional<Permission> findById(Long id);
    public List<Permission> findAll();
}
