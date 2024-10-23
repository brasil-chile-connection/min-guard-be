package com.minguard.service.spec;

import com.minguard.dto.user.RegisterUserRequest;
import com.minguard.dto.user.RegisterUserResponse;
import com.minguard.dto.user.UserResponse;
import com.minguard.util.Roles;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    Long getAuthenticatedUserId();

    UserResponse getAuthenticatedUser();

    List<UserResponse> getAllUsers();

    List<UserResponse> getAllUsersByRole(String roleName);

    UserResponse uploadProfilePicture(MultipartFile file);

    RegisterUserResponse register(RegisterUserRequest request, Roles roleName);

    UserResponse getUserById(Long userId);
}
