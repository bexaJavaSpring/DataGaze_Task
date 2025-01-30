package com.example.datagaze_task.service;

import com.example.datagaze_task.config.CustomUserDetails;
import com.example.datagaze_task.entity.User;
import com.example.datagaze_task.exception.ValidationException;
import com.example.datagaze_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new ValidationException("user.not.found");
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (Objects.nonNull(user.getRoles()))
            user.getRoles().forEach(role -> {
                authorities.add(authority.apply(role.getAuthority()));
            });
        return new CustomUserDetails(user, authorities);
    }

    private final static Function<String, SimpleGrantedAuthority> authority = SimpleGrantedAuthority::new;
}
