package br.com.builders.escolar.controller;

import br.com.builders.escolar.security.model.LoginResponseDTO;
import br.com.builders.escolar.security.model.RegistrationDTO;
import br.com.builders.escolar.security.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody @Valid RegistrationDTO request) {
        return ResponseEntity.ok().body(authenticationService.loginUser(request.username(), request.password()));
    }
}
