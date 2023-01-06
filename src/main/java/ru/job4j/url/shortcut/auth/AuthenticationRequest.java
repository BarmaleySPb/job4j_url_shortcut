package ru.job4j.url.shortcut.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "Site must be not blank")
    private String site;
    @NotBlank(message = "Password must be not blank")
    private String password;
}
