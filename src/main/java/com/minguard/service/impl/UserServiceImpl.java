package com.minguard.service.impl;

import com.minguard.dto.user.UserResponse;
import com.minguard.entity.Role;
import com.minguard.entity.User;
import com.minguard.mapper.UserMapper;
import com.minguard.repository.UserRepository;
import com.minguard.service.spec.RoleService;
import com.minguard.service.spec.UserService;
import com.minguard.util.Roles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public Long getAuthenticatedUserId() {
        Authentication authentication = getAuthentication();

        return ((User) authentication.getPrincipal()).getId();
    }

    @Override
    public UserResponse getAuthenticatedUser() {
        Authentication authentication = getAuthentication();

        return UserMapper.INSTANCE.toResponse((User) authentication.getPrincipal());
    }

    private Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationCredentialsNotFoundException("No authenticated user found");
        }
        return authentication;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        return UserMapper.INSTANCE.toResponses(allUsers);
    }

    @Override
    public void assignRole(User user, Roles role) {
        Role newRole = roleService.getByName(role);
        user.setRole(newRole);
    }

    @Override
    public UserResponse uploadProfilePicture(MultipartFile file) {
        throw new NotImplementedException("Not implemented yet");
    }

}
