package com.example.datagaze_task.service;

import com.example.datagaze_task.config.CustomAuthenticationProvider;
import com.example.datagaze_task.config.CustomUserDetails;
import com.example.datagaze_task.config.UserSession;
import com.example.datagaze_task.dto.AuthLoginDto;
import com.example.datagaze_task.dto.DataDto;
import com.example.datagaze_task.dto.LoginResponse;
import com.example.datagaze_task.dto.UserRegisterDto;
import com.example.datagaze_task.entity.Role;
import com.example.datagaze_task.entity.SessionUser;
import com.example.datagaze_task.entity.Status;
import com.example.datagaze_task.entity.User;
import com.example.datagaze_task.exception.CustomNotFoundException;
import com.example.datagaze_task.repository.RoleRepository;
import com.example.datagaze_task.repository.SessionUserRepository;
import com.example.datagaze_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final SessionUserRepository sessionUserRepository;
    private final UserRepository userRepository;
    private final CustomAuthenticationProvider authenticationProvider;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public DataDto<LoginResponse> login(AuthLoginDto dto) {

        Authentication authenticate = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword()
        ));
        CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
        User user = userRepository.findByIdAndDeleted(userDetails.getId());
        if (user == null) {
            throw new CustomNotFoundException(userDetails.getId().toString(), "user.not.found");
        }
        Optional<SessionUser> optionalSessionUser = sessionUserRepository.findByUserId(user.getId());
        if (optionalSessionUser.isPresent()) {
            SessionUser session = optionalSessionUser.get();
            String accessToken = jwtTokenService.generateToken(userDetails.getUsername());
            String refreshToken = jwtTokenService.generateToken(userDetails.getUsername());
            session.setAccessToken(accessToken);
            session.setRefreshToken(refreshToken);
            session.setStatus(Status.ACTIVE);
            sessionUserRepository.save(session);
            return new DataDto<>(LoginResponse.builder()
                    .access_token(session.getAccessToken())
                    .refresh_token(session.getRefreshToken())
                    .build());
        }
        String accessToken = jwtTokenService.generateToken(userDetails.getUsername());
        String refreshToken = jwtTokenService.generateToken(userDetails.getUsername());
        sessionUserRepository.save(SessionUser.builder()
                .user(user)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .status(Status.ACTIVE)
                .build());
        return new DataDto<>(LoginResponse.builder()
                .access_token(accessToken)
                .refresh_token(refreshToken)
                .build());
    }

    @Override
    public DataDto<Boolean> logout() {
        User user = userRepository.findByIdAndDeleted(UserSession.getCurrentUser().getId());
        if (user == null) {
            throw new CustomNotFoundException("user.not.found");
        }
        Optional<SessionUser> sessionOptional = sessionUserRepository.findByUserId(user.getId());
        if (sessionOptional.isPresent()) {
            SessionUser session = sessionOptional.get();
            session.setStatus(Status.DISABLED);
            sessionUserRepository.save(session);
        }
        return new DataDto<>(true);
    }

    @Override
    public DataDto<UUID> register(UserRegisterDto dto) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        Role role = roleRepository.findByCode("USER");
        user.setRoles(Arrays.asList(role));
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        User save = userRepository.save(user);
        return new DataDto<>(save.getId());
    }
}
