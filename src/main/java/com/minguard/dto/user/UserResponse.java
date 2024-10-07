package com.minguard.dto.user;

import com.minguard.dto.gender.GenderResponse;
import com.minguard.dto.role.RoleResponse;
import java.time.LocalDateTime;

public record UserResponse(

        Long id,

        String email,

        String firstName,

        String lastName,

        String mobilePrefix,

        String mobileNumber,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        Boolean acceptTc,

        GenderResponse gender,

        RoleResponse role,

        String profilePicturePath
) {

}
