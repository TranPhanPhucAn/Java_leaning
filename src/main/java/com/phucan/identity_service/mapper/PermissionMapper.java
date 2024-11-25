package com.phucan.identity_service.mapper;

import com.phucan.identity_service.dto.request.PermissionCreationRequest;
import com.phucan.identity_service.dto.response.PermissionResponse;
import com.phucan.identity_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionCreationRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
//    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
