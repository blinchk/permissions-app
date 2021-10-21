package com.laus.permissions.controller;

import com.laus.permissions.exception.NotFoundException;
import com.laus.permissions.model.Permission;
import com.laus.permissions.service.PermissionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/permission")
public class PermissionController {

    private PermissionService permissionService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Permission> Get() {
        return permissionService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Permission GetById(@PathVariable String id) {
        long parsedId = Long.parseLong(id);
        Optional<Permission> permissionResult = permissionService.findById(parsedId);
        return permissionResult.orElseThrow(NotFoundException::new);
    }
}
