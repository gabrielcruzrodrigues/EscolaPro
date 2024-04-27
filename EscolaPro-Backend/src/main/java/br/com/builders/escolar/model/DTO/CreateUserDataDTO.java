package br.com.builders.escolar.model.DTO;

import br.com.builders.escolar.model.enums.RoleEnum;

public record CreateUserDataDTO(
        String username,
        String password,
        RoleEnum role
) {
}
