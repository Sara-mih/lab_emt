package mk.finki.ukim.mk.lab_emt.model.dto;

import mk.finki.ukim.mk.lab_emt.model.domain.Role;
import mk.finki.ukim.mk.lab_emt.model.domain.User;

public record RegisterUserResponseDto(
        String username,
        Role role
) {
    public static RegisterUserResponseDto from(User user) {
        return new RegisterUserResponseDto(
                user.getUsername(),
                user.getRole()
        );
    }
}