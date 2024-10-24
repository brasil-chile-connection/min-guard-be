package com.minguard.controller;

import com.minguard.dto.user.RegisterUserRequest;
import com.minguard.dto.user.RegisterUserResponse;
import com.minguard.dto.user.UserResponse;
import com.minguard.service.spec.UserService;
import com.minguard.util.Roles;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Tag(name = "User Controller")
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @Operation(summary = "Get authenticated user", description = "Get details of the authenticated user.")
    public ResponseEntity<UserResponse> authenticatedUser() {
        UserResponse user = userService.getAuthenticatedUser();

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all users", description = "Get details of all registered users. Must have admin role.")
    public ResponseEntity<List<UserResponse>> allUsers() {
        List<UserResponse> users = userService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

//    TODO: copied from base project, need to implement
    @PostMapping("/profile-picture")
    public ResponseEntity<UserResponse> uploadProfilePicture(@RequestParam("file") MultipartFile file) {
        UserResponse updatedUser = userService.uploadProfilePicture(file);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/worker/register")
    @Operation(summary = "Create a new worker user", description = "Creates a new worker user in the system.")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request) {

        RegisterUserResponse registeredUser = userService.register(request, Roles.WORKER);

        return ResponseEntity.ok(registeredUser);
    }


}
