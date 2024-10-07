package com.minguard.service.spec;

import com.minguard.dto.user.UserResponse;
import com.minguard.entity.User;
import com.minguard.util.Roles;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    Long getAuthenticatedUserId();

    UserResponse getAuthenticatedUser();

    List<UserResponse> getAllUsers();

    void assignRole(User user, Roles role);

    UserResponse uploadProfilePicture(MultipartFile file);
}
