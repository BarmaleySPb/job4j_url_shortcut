package ru.job4j.url.shortcut.model.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortcutDTO {
    @NotBlank(message = "Url must be not empty")
    private String url;

}
