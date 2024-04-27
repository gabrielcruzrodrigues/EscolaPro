package br.com.builders.escolar.security.service;

import br.com.builders.escolar.model.DTO.CreateUserDataDTO;
import br.com.builders.escolar.security.model.User;
import br.com.builders.escolar.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createUser(CreateUserDataDTO data) {
        User user = User.builder()
                .username(data.username())
                .password(data.password())
                .role(Set.of(data.role().toString()))
                .build();
        this.userRepository.save(user);
    }
}
