package mk.finki.ukim.mk.lab_emt.service;

import mk.finki.ukim.mk.lab_emt.model.dto.LoginUserRequestDto;
import mk.finki.ukim.mk.lab_emt.model.dto.LoginUserResponseDto;
import mk.finki.ukim.mk.lab_emt.model.dto.RegisterUserRequestDto;
import mk.finki.ukim.mk.lab_emt.model.dto.RegisterUserResponseDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto);
    Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto);
    Optional<RegisterUserResponseDto> findByUsername(String username);
}