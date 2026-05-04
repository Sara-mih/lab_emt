package mk.finki.ukim.mk.lab_emt.model.dto;

import mk.finki.ukim.mk.lab_emt.model.domain.Role;
import mk.finki.ukim.mk.lab_emt.model.domain.User;

public record RegisterUserRequestDto(
        String username,
        String password,
        Role role
) {
    public User toUser() {
        return new User(username, password, role != null ? role : Role.ROLE_USER);
    }
}