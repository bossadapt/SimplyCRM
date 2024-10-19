package SimplyCRM.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import SimplyCRM.model.LoginRequest;
import SimplyCRM.model.LoginResponse;
import SimplyCRM.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin()
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request ){
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }
}
