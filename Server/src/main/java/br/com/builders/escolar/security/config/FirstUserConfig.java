package br.com.builders.escolar.security.config;

import br.com.builders.escolar.model.enums.RoleEnum;
import br.com.builders.escolar.security.model.User;
import br.com.builders.escolar.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class FirstUserConfig implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.count() != 0) {
            return;
        }

        log.info("Nenhum usuário encontrado, cadastrando usuários padrão.");

        userRepository.save(
                User.builder()
                        .id(1L)
                        .username("admin")
                        .password(passwordEncoder.encode("123456"))
                        .role(Set.of("ADMIN", "PEDAGOGICAL"))
                        .build()
        );
    }
}
