package com.phucan.identity_service.service;

import com.phucan.identity_service.dto.request.UserCreationRequest;
import com.phucan.identity_service.dto.response.UserResponse;
import com.phucan.identity_service.entity.User;
import com.phucan.identity_service.exception.AppException;
import com.phucan.identity_service.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData(){
        dob = LocalDate.of(1998, 2, 2);
        request = UserCreationRequest.builder()
                .username("Hau")
                .firstName("Le Trung")
                .lastName("Hau")
                .password("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("dbc123445")
                .username("Hau")
                .firstName("Le Trung")
                .lastName("Hau")
                .dob(dob)
                .build();

        user = User.builder()
                .id("dbc123445")
                .username("Hau")
                .firstName("Le Trung")
                .lastName("Hau")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success(){
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);

        //THEN
        Assertions.assertThat(response.getId()).isEqualTo("dbc123445");
        Assertions.assertThat(response.getUsername()).isEqualTo("Hau");
    }

    @Test
    void createUser_userExisted_fail(){
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // WHEN
        var exception = assertThrows(AppException.class,
                () -> userService.createUser(request));

        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode())
                .isEqualTo(1002);
    }
}
