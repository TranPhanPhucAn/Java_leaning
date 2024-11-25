package com.phucan.identity_service.controller;

import com.phucan.identity_service.dto.request.ApiResponse;
import com.phucan.identity_service.dto.request.RoleCreationRequest;
import com.phucan.identity_service.dto.response.RoleResponse;
import com.phucan.identity_service.entity.Role;
import com.phucan.identity_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleCreationRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<String> delete(@PathVariable String role){
        roleService.delete(role);
        return ApiResponse.<String>builder()
                .result("Role has been delete")
                .build();
    }
}
