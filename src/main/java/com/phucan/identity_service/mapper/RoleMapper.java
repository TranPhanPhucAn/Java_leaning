package com.phucan.identity_service.mapper;

import com.phucan.identity_service.dto.request.PermissionCreationRequest;
import com.phucan.identity_service.dto.request.RoleCreationRequest;
import com.phucan.identity_service.dto.response.PermissionResponse;
import com.phucan.identity_service.dto.response.RoleResponse;
import com.phucan.identity_service.entity.Permission;
import com.phucan.identity_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleCreationRequest request);

    RoleResponse toRoleResponse(Role role);
//    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
