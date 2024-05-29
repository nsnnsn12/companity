package com.codecrafters.companity.adapter.user;

import com.codecrafters.companity.adapter.user.dto.request.UserCreateRequest;
import com.codecrafters.companity.adapter.user.dto.resposne.UserCreateResponse;
import com.codecrafters.companity.application.in.usecase.UserUseCase;
import com.codecrafters.companity.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<UserCreateResponse> signUp (@RequestBody UserCreateRequest request) {
        User user = userUseCase.signUp(request);
        UserCreateResponse response = UserCreateResponse.from(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
