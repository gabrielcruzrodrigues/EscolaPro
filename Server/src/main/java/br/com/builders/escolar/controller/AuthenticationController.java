package br.com.builders.escolar.controller;

import br.com.builders.escolar.security.model.AuthenticatedLoginDTO;
import br.com.builders.escolar.security.model.RegistrationDTO;
import br.com.builders.escolar.security.model.ResponseLoginDTO;
import br.com.builders.escolar.security.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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
    public ResponseEntity<ResponseLoginDTO> loginUser(@RequestBody @Valid RegistrationDTO request, HttpServletResponse response) {
        AuthenticatedLoginDTO result = authenticationService.loginUser(request.username(), request.password());

        if (result.login()) {
//            long expirationTime = 86400000;
//            ResponseCookie jwtCookie = ResponseCookie.from("token", result.token())
//                    .httpOnly(false)
//                    .secure(false)  //change to true in production
//                    .path("/")
//                    .maxAge(expirationTime / 1000)
//                    .sameSite("Strict")
//                    .domain("localhost")
//                    .build();
//
//            response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());
            ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO(result.username(), result.token());

            return ResponseEntity.ok().body(responseLoginDTO);
        } else {
            return new ResponseEntity<>(new ResponseLoginDTO(result.username(), "User not authenticated"), HttpStatus.UNAUTHORIZED);
        }
    }
}
