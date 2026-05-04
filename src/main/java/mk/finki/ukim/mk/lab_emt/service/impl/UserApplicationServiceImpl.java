package mk.finki.ukim.mk.lab_emt.service.impl;

import mk.finki.ukim.mk.lab_emt.helpers.JwtHelper;
import mk.finki.ukim.mk.lab_emt.model.domain.User;
import mk.finki.ukim.mk.lab_emt.model.dto.LoginUserRequestDto;
import mk.finki.ukim.mk.lab_emt.model.dto.LoginUserResponseDto;
import mk.finki.ukim.mk.lab_emt.model.dto.RegisterUserRequestDto;
import mk.finki.ukim.mk.lab_emt.model.dto.RegisterUserResponseDto;
import mk.finki.ukim.mk.lab_emt.service.UserApplicationService;
import mk.finki.ukim.mk.lab_emt.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto) {
        User user = userService.register(registerUserRequestDto.toUser());
        return Optional.of(RegisterUserResponseDto.from(user));
    }

    @Override
    public Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto) {
        User user = userService.login(loginUserRequestDto.username(), loginUserRequestDto.password());
        String token = jwtHelper.generateToken(user);
        return Optional.of(new LoginUserResponseDto(token));
    }

    @Override
    public Optional<RegisterUserResponseDto> findByUsername(String username) {
        return userService.findByUsername(username)
                .map(RegisterUserResponseDto::from);
    }
}