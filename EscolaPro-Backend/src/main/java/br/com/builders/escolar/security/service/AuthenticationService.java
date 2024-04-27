package br.com.builders.escolar.security.service;

import br.com.builders.escolar.security.model.AuthenticatedLoginDTO;
import br.com.builders.escolar.security.model.User;
import br.com.builders.escolar.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticatedLoginDTO loginUser(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenService.generateToken(auth);
            Optional<User> user = userRepository.findByUsername(username);
            return new AuthenticatedLoginDTO(user.get().getUsername(), token, true);
        } catch (Exception ex) {
            return new AuthenticatedLoginDTO(null, ex.getMessage(), false);
        }
    }
}
