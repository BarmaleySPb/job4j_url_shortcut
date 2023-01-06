package ru.job4j.url.shortcut.auth;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.model.Account;
import ru.job4j.url.shortcut.model.Role;
import ru.job4j.url.shortcut.security.JwtService;
import ru.job4j.url.shortcut.service.AccountService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest registerRequest) {
        String site = registerRequest.getSite();
        if (accountService.findBySite(site).isPresent()) {
            throw new IllegalArgumentException("You are already registered");
        }
        String password = RandomStringUtils.randomAscii(8);
        var user = Account.builder()
                .site(registerRequest.getSite())
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();
        accountService.save(user);
        return RegisterResponse.builder()
                .site(registerRequest.getSite())
                .password(password)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest registerRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerRequest.getSite(),
                        registerRequest.getPassword()
                )
        );
        var user = accountService.findBySite(registerRequest.getSite()).orElseThrow(
                () -> new NoSuchElementException("Account not found")
        );
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
