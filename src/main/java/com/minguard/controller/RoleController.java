package com.minguard.controller;

import com.minguard.dto.role.RoleResponse;
import com.minguard.service.spec.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "Role Controller")
@RequestMapping("/role")
@RestController
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @Operation(summary = "Get all roles", description = "Get all registered roles.")
    public ResponseEntity<List<RoleResponse>> getRoles() {
        List<RoleResponse> roles = roleService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

}
