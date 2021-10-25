package com.laus.permissions.controller;

import com.laus.permissions.exception.NotFoundException;
import com.laus.permissions.model.Permission;
import com.laus.permissions.repository.PermissionRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/permission")
public class PermissionController {
    private final PermissionRepository repository;

    public PermissionController(PermissionRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Permission> all() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Permission one(@PathVariable String id) {
        long parsedId = Long.parseLong(id);
        Optional<Permission> permissionResult = repository.findById(parsedId);
        return permissionResult.orElseThrow(NotFoundException::new);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Permission newPermission(@RequestBody Permission permission) {
        return repository.save(permission);
    }
}
